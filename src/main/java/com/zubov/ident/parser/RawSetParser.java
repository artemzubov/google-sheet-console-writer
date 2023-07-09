package com.zubov.ident.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                objects.add(object != null ? object.toString() : ""); // TODO log it
            }
            lists.add(objects);
        }
        return lists;
    }
}
