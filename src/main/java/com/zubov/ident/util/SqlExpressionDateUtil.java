package com.zubov.ident.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SqlExpressionDateUtil {

  private static final String DATE_PLACEHOLDER = "DATE_PLACEHOLDER";

  public static String insertCurrentDate(String sqlExpressionWithPlaceHolder) {
    LocalDate yesterday = LocalDate.now().minusDays(1);
    String dateFormatted = yesterday.format(DateTimeFormatter.ISO_LOCAL_DATE);
    return sqlExpressionWithPlaceHolder.replace(DATE_PLACEHOLDER, dateFormatted);
  }
}
