package com.zubov.ident.util;

import com.zubov.ident.model.FilesToClean;
import com.zubov.ident.model.SqlWithProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FilesParserUtil {

  private static final String SPLITTER = "###";

  public static SqlWithProperties parseSqlWithProperties(String sqlWithProperties)
      throws ArrayIndexOutOfBoundsException {
    String[] split = sqlWithProperties.split(SPLITTER);
    return SqlWithProperties.builder()
        .sqlPath(split[0])
        .googleDocId(split[1])
        .googleDocPageId(split[2])
        .build();
  }

  public static FilesToClean parseFilesToClean(String filesToClean)
      throws ArrayIndexOutOfBoundsException {
    String[] split = filesToClean.split(SPLITTER);
    return FilesToClean.builder().googleDocId(split[0]).googleDocPageId(split[1]).build();
  }
}
