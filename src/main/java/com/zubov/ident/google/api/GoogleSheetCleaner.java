package com.zubov.ident.google.api;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.zubov.ident.util.AuthorizationUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class GoogleSheetCleaner {
  private static final Credential CREDENTIALS = AuthorizationUtil.getCredentials();
  private static final String IDENT_ADDON = "Ident addon";
  private static final String RANGE = "!A2:Z";

  @SneakyThrows
  public void clear(String googleDocId, String googleDocPageId) {
    // Create the sheets API client
    // TODO refactor to bean
    Sheets service =
        new Sheets.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), CREDENTIALS)
            .setApplicationName(IDENT_ADDON)
            .build();

    service
        .spreadsheets()
        .values()
        .clear(googleDocId, googleDocPageId + RANGE, new ClearValuesRequest())
        .execute();
  }
}
