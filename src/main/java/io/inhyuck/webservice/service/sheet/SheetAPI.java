package io.inhyuck.webservice.service.sheet;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import io.inhyuck.webservice.config.MySheetProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class SheetAPI {
    @Autowired
    MySheetProperties sheetProperties;

    /**
     * OAUTH 2.0 연동시 지정한 OAuth 2.0 클라이언트 이름
     */
    private static final String APPLICATION_NAME =
            "sheet-crawler";

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Google Sheet API 권한을 SCOPE로 지정
     */
    private static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS);

    /**
     * HTTP_TRANSPORT
     */
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Service Account용 credentail
     * @return Credential object.
     * @throws IOException
     */
    public static Credential getServiceAccountAuthorize() throws IOException {

        InputStream in =
                SheetAPI.class.getResourceAsStream("/recruit-service.json");
        Credential credential = GoogleCredential.fromStream(in)
                .createScoped(SCOPES);
        return credential;
    }

    /**
     * Google Credential 정보를 가지고 Google Sheet서비스를 초기화 한다.
     * @return 인증이 통과된 Sheets API client service
     * @throws IOException
     */
    public static Sheets getSheetsService() throws IOException {
        Credential credential = getServiceAccountAuthorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public List<List<Object>> findAll(String spreadsheetId) throws IOException {
        Sheets service = getSheetsService();
        String range = sheetProperties.getSheetName()+ "!" + sheetProperties.getStartLow() + ":" + sheetProperties.getLastLow();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        return values;
    }

    public List<Object> findOne(String spreadsheetId, String rowId) throws IOException {
        Sheets service = getSheetsService();
        String range = sheetProperties.getSheetName()+ "!" + rowId + ":" + rowId;
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        return values.get(0);
    }

}
