package io.inhyuck.webservice.dao;

import io.inhyuck.webservice.config.MySheetProperties;
import io.inhyuck.webservice.domain.resume.ResumeDesign;
import io.inhyuck.webservice.domain.resume.ResumeDevelop;
import io.inhyuck.webservice.domain.resume.ResumeSimple;
import io.inhyuck.webservice.domain.resume.UrlList;
import io.inhyuck.webservice.dao.sheet.SheetAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResumeDAO {
    static final String DEVELOPER = "developer";
    static final String DESIGNER = "designer";

    @Autowired
    private MySheetProperties sheetProperties;
    @Autowired
    private SheetAPI sheetAPI;

    public List<ResumeSimple> findAll(String role) throws IOException {
        List<List<Object>> values = null;
        String range;
        if (role.equals(DEVELOPER)) {
            range = getRange(sheetProperties.getDeveloperSheetName()
                    , sheetProperties.getStartLow()
                    , sheetProperties.getLastLow());
            values = sheetAPI.getValues(sheetProperties.getDeveloperSheetId(), range).getValues();
        } else if (role.equals(DESIGNER)){
            range = getRange(sheetProperties.getDesignerSheetName()
                    , sheetProperties.getStartLow()
                    , sheetProperties.getLastLow());
            values = sheetAPI.getValues(sheetProperties.getDesignerSheetId(), range).getValues();
        }

        List<ResumeSimple> resumeList = new ArrayList<>();
        ResumeSimple resumeSimple;
        int rowId = 2;
        for (List<Object> value : values) {
            resumeSimple = ResumeSimple.builder()
                    .rowId(String.valueOf(rowId++))
                    .timestamp(value.get(0).toString())
                    .name(value.get(1).toString())
                    .email(value.get(2).toString())
                    .phoneNumber((value.get(3).toString()))
                    .question1(value.get(4).toString())
                    .question2(value.get(5).toString())
                    .question2_2(value.get(6).toString())
                    .build();
            resumeList.add(resumeSimple);
        }
        return resumeList;
    }

    public ResumeDesign findOneDesigner(String rowId) throws IOException {
        List<Object> values = sheetAPI.getValues(sheetProperties.getDesignerSheetId()
                , getRange(sheetProperties.getDesignerSheetName(), rowId, rowId)).getValues().get(0);
        return ResumeDesign.builder()
                .rowId(Long.parseLong(rowId))
                .timestamp(values.get(0).toString())
                .name(values.get(1).toString())
                .email(values.get(2).toString())
                .phoneNumber(values.get(3).toString())
                .question1(values.get(4).toString())
                .question2(values.get(5).toString())
                .question2_2(values.get(6).toString())
                .question3(values.get(7).toString())
                .question4(values.get(8).toString())
                .question5(values.get(9).toString())
                .question6(values.get(10).toString())
                .portfolioLink(values.get(11).toString())
                .build();
    }

    public ResumeDevelop findOneDeveloper(String rowId) throws IOException {
        List<Object> values = sheetAPI.getValues(sheetProperties.getDeveloperSheetId()
                , getRange(sheetProperties.getDeveloperSheetName(), rowId, rowId)).getValues().get(0);
        return ResumeDevelop.builder()
                .rowId(Long.parseLong(rowId))
                .timestamp(values.get(0).toString())
                .name(values.get(1).toString())
                .email(values.get(2).toString())
                .phoneNumber((values.get(3).toString()))
                .question1(values.get(4).toString())
                .question2(values.get(5).toString())
                .question2_2(values.get(6).toString())
                .question3(values.get(7).toString())
                .question4(values.get(8).toString())
                .question5(values.get(9).toString())
                .question6(values.get(10).toString())
                .question7(values.get(11).toString())
                .urlList((values.size() == 13) ? new UrlList(values.get(12).toString()) : new UrlList("무응답"))
                .build();
    }

    public String getRange(String sheetName, String startRow, String lastRow) {
        return new StringBuilder()
                .append(sheetName)
                .append("!")
                .append(startRow)
                .append(":")
                .append(lastRow)
                .toString();
    }
}
