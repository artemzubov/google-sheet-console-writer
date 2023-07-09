package com.zubov.ident.task;

import com.zubov.ident.appender.GoogleSheetAppender;
import com.zubov.ident.executor.SqlExecutor;
import com.zubov.ident.parser.RawSetParser;
import com.zubov.ident.util.FilesUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DailyTask {
    private final GoogleSheetAppender googleSheetAppender;
    private final SqlExecutor sqlExecutor;
    private final RawSetParser rawSetParser;

    // TODO change order of columns
    // TODO change date
    // TODO handle exceptions
    @Scheduled(cron = "0 0 5 * * *") //every day at 05:00:00
//    @Scheduled(cron = "*/10 * * * * *") //TODO
    public void mainJob() {
        try {
            FilesUtil.getListOfSqlFiles().forEach(this::processFile);
        } catch (Exception e) {
            log.error("Couldn't even read file with sqlList");
        }
    }

    private void processFile(String fileWithSql) {
        try {
            String sqlExpression = FilesUtil.getSqlExpression(fileWithSql);
            SqlRowSet sqlRowSet = sqlExecutor.executeSql(sqlExpression);
            List<List<? super Object>> lists = rawSetParser.parseSqlRawSet(sqlRowSet);
            googleSheetAppender.append(lists);
        } catch (DataAccessException e) {
            log.error("Couldn't execute sql expression");
        } catch (IOException e) {
            log.error("IOException for file {}. Exception message is: {}", fileWithSql, e.getMessage());
        } catch (Exception e) {
            log.error("Something went wrong for file {}. Exception message is: {}", fileWithSql, e.getMessage());
        }
    }
}
