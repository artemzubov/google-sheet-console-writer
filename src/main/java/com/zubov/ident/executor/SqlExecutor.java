package com.zubov.ident.executor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SqlExecutor {
    private final JdbcTemplate jdbcTemplate;

    public SqlRowSet executeSql(String sqlExpression) {
        return jdbcTemplate.queryForRowSet(sqlExpression);
    }
}
