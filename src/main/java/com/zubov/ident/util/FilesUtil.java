package com.zubov.ident.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilesUtil {

    private static final String LIST_OF_SQL = "listOfSql.txt";

    public static String getSqlExpression(String listOfSqlFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(listOfSqlFile)));
    }

    public static List<String> getListOfSqlFiles() throws IOException {
        return Files.readAllLines(Paths.get(LIST_OF_SQL), Charset.defaultCharset());
    }
}
