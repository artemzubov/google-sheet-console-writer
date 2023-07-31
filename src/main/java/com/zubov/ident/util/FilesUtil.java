package com.zubov.ident.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FilesUtil {

  // TODO move to app.yml
  private static final String ROOT = "C:\\IDENT_ADDON\\";
  private static final String LIST_OF_SQL = ROOT + "listOfSql.txt";
  private static final String FILES_TO_CLEAN = ROOT + "filesToClean.txt";

  public static List<String> getListOfSqlFiles() throws IOException {
    return Files.readAllLines(Paths.get(LIST_OF_SQL), Charset.defaultCharset());
  }

  public static String getSqlExpression(String fileWithSql) throws IOException {
    return new String(Files.readAllBytes(Paths.get(ROOT + fileWithSql)));
  }

  public static List<String> getFilesToClean() throws IOException {
    return Files.readAllLines(Paths.get(FILES_TO_CLEAN), Charset.defaultCharset());
  }
}
