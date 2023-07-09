package com.zubov.ident.appender;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.zubov.ident.util.AuthorizationUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class GoogleSheetAppender {
    private static final Credential CREDENTIALS = AuthorizationUtil.getCredentials();
    private static final String VALUE_INPUT_OPTION = "USER_ENTERED";
    private static final String IDENT_ADDON = "Ident addon";
    private static final String RANGE = "List1!A1:Z";

    @SneakyThrows
    public void append(List<List<? super Object>> lists) {
        // Create the sheets API client
        Sheets service = new Sheets.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                CREDENTIALS)
                .setApplicationName(IDENT_ADDON)
                .build();

        ValueRange body = new ValueRange()
                .setValues(lists);
        service.spreadsheets().values().append("1LIVUMDYk5j70zRE0SUqn3xtbWmv7oi4jaQhpxmTvWAA", RANGE, body)
                .setValueInputOption(VALUE_INPUT_OPTION)
                .execute();
    }
}
