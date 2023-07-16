package com.zubov.ident.util;

import com.zubov.ident.model.SqlWithProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageToSqlWithPropertiesUtil {

  private static final String SPLITTER = "###";

  public static SqlWithProperties parse(String sqlWithProperties)
      throws ArrayIndexOutOfBoundsException {
    String[] split = sqlWithProperties.split(SPLITTER);
    return SqlWithProperties.builder()
        .sqlPath(split[0])
        .googleDocId(split[1])
        .googleDocPageId(split[2])
        .build();
  }
}
