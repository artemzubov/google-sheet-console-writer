package com.zubov.ident.google.api;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.zubov.ident.util.AuthorizationUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class GoogleSheetAppender {
  private static final Credential CREDENTIALS = AuthorizationUtil.getCredentials();
  private static final String VALUE_INPUT_OPTION = "USER_ENTERED";
  private static final String IDENT_ADDON = "Ident addon";
  private static final String RANGE = "!A:E";

  @SneakyThrows
  public void append(List<List<? super Object>> lists, String googleDocId, String googleDocPageId) {
    // Create the sheets API client
    Sheets service =
        new Sheets.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), CREDENTIALS)
            .setApplicationName(IDENT_ADDON)
            .build();

    ValueRange body = new ValueRange().setValues(lists);
    service
        .spreadsheets()
        .values()
        .append(googleDocId, googleDocPageId + RANGE, body)
        .setValueInputOption(VALUE_INPUT_OPTION)
        .execute();
  }
}
