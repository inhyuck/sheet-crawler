package io.inhyuck.webservice.dao.sheet;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SheetAPI {
    @Autowired
    private SheetConfig sheetConfig;

    public String create(String title) throws IOException {
        Sheets service = sheetConfig.getSheetsService();
        // [START sheets_create]
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle(title));
        spreadsheet = service.spreadsheets().create(spreadsheet)
                .setFields("spreadsheetId")
                .execute();
//        System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
        // [END sheets_create]
        return spreadsheet.getSpreadsheetId();
    }

    public BatchUpdateSpreadsheetResponse batchUpdate(String spreadsheetId, String title,
                                                      String find, String replacement)
            throws IOException {
        Sheets service = sheetConfig.getSheetsService();
        // [START sheets_batch_update]
        List<Request> requests = new ArrayList<>();
        // Change the spreadsheet's title.
        requests.add(new Request()
                .setUpdateSpreadsheetProperties(new UpdateSpreadsheetPropertiesRequest()
                        .setProperties(new SpreadsheetProperties()
                                .setTitle(title))
                        .setFields("title")));
        // Find and replace text.
        requests.add(new Request()
                .setFindReplace(new FindReplaceRequest()
                        .setFind(find)
                        .setReplacement(replacement)
                        .setAllSheets(true)));
        // Add additional requests (operations) ...

        BatchUpdateSpreadsheetRequest body =
                new BatchUpdateSpreadsheetRequest().setRequests(requests);
        BatchUpdateSpreadsheetResponse response =
                service.spreadsheets().batchUpdate(spreadsheetId, body).execute();
        FindReplaceResponse findReplaceResponse = response.getReplies().get(1).getFindReplace();
//        System.out.printf("%d replacements made.", findReplaceResponse.getOccurrencesChanged());
        // [END sheets_batch_update]
        return response;
    }

    public ValueRange getValues(String spreadsheetId, String range) throws IOException {
        Sheets service = sheetConfig.getSheetsService();
        // [START sheets_get_values]
        ValueRange result = service.spreadsheets().values().get(spreadsheetId, range).execute();
        int numRows = result.getValues() != null ? result.getValues().size() : 0;
//        System.out.printf("%d rows retrieved.", numRows);
        // [END sheets_get_values]
        return result;
    }

    public BatchGetValuesResponse batchGetValues(String spreadsheetId, List<String> _ranges)
            throws IOException {
        Sheets service = sheetConfig.getSheetsService();
        // [START sheets_batch_get_values]
        List<String> ranges = Arrays.asList(
                //Range names ...
        );
        // [START_EXCLUDE silent]
        ranges = _ranges;
        // [END_EXCLUDE]
        BatchGetValuesResponse result = service.spreadsheets().values().batchGet(spreadsheetId)
                .setRanges(ranges).execute();
//        System.out.printf("%d ranges retrieved.", result.getValueRanges().size());
        // [END sheets_batch_get_values]
        return result;
    }
    public UpdateValuesResponse updateValues(String spreadsheetId, String range,
                                             String valueInputOption, List<List<Object>> _values)
            throws IOException {
        Sheets service = sheetConfig.getSheetsService();
        // [START sheets_update_values]
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(
                        // Cell values ...
                )
                // Additional rows ...
        );
        // [START_EXCLUDE silent]
        values = _values;
        // [END_EXCLUDE]
        ValueRange body = new ValueRange()
                .setValues(values);
        UpdateValuesResponse result =
                service.spreadsheets().values().update(spreadsheetId, range, body)
                        .setValueInputOption(valueInputOption)
                        .execute();
        System.out.printf("%d cells updated.", result.getUpdatedCells());
        // [END sheets_update_values]
        return result;
    }

    public BatchUpdateValuesResponse batchUpdateValues(String spreadsheetId, String range,
                                                       String valueInputOption,
                                                       List<List<Object>> _values)
            throws IOException {
        Sheets service = sheetConfig.getSheetsService();
        // [START sheets_batch_update_values]
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(
                        // Cell values ...
                )
                // Additional rows ...
        );
        // [START_EXCLUDE silent]
        values = _values;
        // [END_EXCLUDE]
        List<ValueRange> data = new ArrayList<ValueRange>();
        data.add(new ValueRange()
                .setRange(range)
                .setValues(values));
        // Additional ranges to update ...

        BatchUpdateValuesRequest body = new BatchUpdateValuesRequest()
                .setValueInputOption(valueInputOption)
                .setData(data);
        BatchUpdateValuesResponse result =
                service.spreadsheets().values().batchUpdate(spreadsheetId, body).execute();
        System.out.printf("%d cells updated.", result.getTotalUpdatedCells());
        // [END sheets_batch_update_values]
        return result;
    }
}
