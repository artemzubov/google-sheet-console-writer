package com.zubov.ident.parser;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RawSetParser {

  public List<List<? super Object>> parseSqlRawSet(SqlRowSet sqlRowSet) {
    List<List<? super Object>> lists = new ArrayList<>();
    while (sqlRowSet.next()) {
      List<? super Object> objects = new ArrayList<>();

      int columnCount = sqlRowSet.getMetaData().getColumnCount();
      for (int i = 1; i <= columnCount; i++) {
        Object object = sqlRowSet.getObject(i);
        objects.add(object != null ? tryParse(object) : "");
      }
      lists.add(objects);
    }
    return lists;
  }

  // experimental
  private Object tryParse(Object object) {
    String s = object.toString();
    if (s.matches("\\d+\\.\\d+")) {
      return s.replace(".", ",");
    }
    return s;
  }
}
