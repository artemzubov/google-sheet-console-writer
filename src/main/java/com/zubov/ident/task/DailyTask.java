package com.zubov.ident.task;

import com.zubov.ident.executor.SqlExecutor;
import com.zubov.ident.google.api.GoogleSheetAppender;
import com.zubov.ident.google.api.GoogleSheetCleaner;
import com.zubov.ident.model.SqlWithProperties;
import com.zubov.ident.parser.RawSetParser;
import com.zubov.ident.util.FilesParserUtil;
import com.zubov.ident.util.FilesUtil;
import com.zubov.ident.util.SqlExpressionDateUtil;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DailyTask {
  private final GoogleSheetAppender googleSheetAppender;
  private final GoogleSheetCleaner googleSheetCleaner;
  private final SqlExecutor sqlExecutor;
  private final RawSetParser rawSetParser;

  @Scheduled(cron = "0 0 5 * * *") // every day at 05:00:00
  public void mainJob() {
    try {
      FilesUtil.getFilesToClean().stream()
          .map(FilesParserUtil::parseFilesToClean)
          .forEach(
              file -> googleSheetCleaner.clear(file.getGoogleDocId(), file.getGoogleDocPageId()));
    } catch (Exception e) {
      log.error("Couldn't even read files to clean");
    }
    try {
      FilesUtil.getListOfSqlFiles().stream()
          .map(FilesParserUtil::parseSqlWithProperties)
          .forEach(this::processFile);
    } catch (Exception e) {
      log.error("Couldn't even read file with sqlList");
    }
  }

  // TODO Async
  private void processFile(SqlWithProperties sqlWithProperties) {
    try {
      String sqlExpression = FilesUtil.getSqlExpression(sqlWithProperties.getSqlPath());
      sqlExpression = SqlExpressionDateUtil.insertCurrentDate(sqlExpression);
      SqlRowSet sqlRowSet = sqlExecutor.executeSql(sqlExpression);
      List<List<? super Object>> rowsAndColumnsResult = rawSetParser.parseSqlRawSet(sqlRowSet);
      googleSheetAppender.append(
          rowsAndColumnsResult,
          sqlWithProperties.getGoogleDocId(),
          sqlWithProperties.getGoogleDocPageId());
    } catch (DataAccessException e) {
      log.error("Couldn't execute sql expression");
    } catch (IOException e) {
      log.error(
          "IOException for file {}. Exception message is: {}",
          sqlWithProperties.getSqlPath(),
          e.getMessage());
    } catch (Exception e) {
      log.error(
          "Something went wrong for file {}. Exception message is: {}",
          sqlWithProperties.getSqlPath(),
          e.getMessage());
    }
  }
}
