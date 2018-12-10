package io.inhyuck.webservice.dao;

import io.inhyuck.webservice.config.MySheetProperties;
import io.inhyuck.webservice.entity.resume.ResumeDesign;
import io.inhyuck.webservice.entity.resume.ResumeDevelop;
import io.inhyuck.webservice.entity.resume.ResumeSimple;
import io.inhyuck.webservice.entity.resume.UrlList;
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
                    .checkA(getCheckValue(value.get(value.size() - 4).toString(), role.equals(DEVELOPER) ? "cto" : ""))
                    .checkB(getCheckValue(value.get(value.size() - 3).toString(), role.equals(DEVELOPER) ? "coo" : "ceo"))
                    .checkC(getCheckValue(value.get(value.size() - 2).toString(), role.equals(DEVELOPER) ? "cmo" : "cdo"))
                    .pageTest(value.get(value.size() - 1).toString())
                    .build();
            resumeList.add(resumeSimple);
        }
        return resumeList;
    }

    public ResumeDevelop findOneDeveloper(String rowId) throws IOException {
        List<List<Object>> values = sheetAPI.getValues(sheetProperties.getDeveloperSheetId()
                , getRange(sheetProperties.getDeveloperSheetName(), sheetProperties.getStartLow(), sheetProperties.getLastLow())).getValues();
        String lastRowNumber = String.valueOf(values.size() + 1);
        List<Object> value = values.get(Integer.parseInt(rowId) - 2);
        if (value.size() < 16) {
            value.add(12, "No answer!");
        }
        return ResumeDevelop.builder()
                .rowId(Long.parseLong(rowId))
                .timestamp(value.get(0).toString())
                .name(value.get(1).toString())
                .email(value.get(2).toString())
                .phoneNumber((value.get(3).toString()))
                .question1(value.get(4).toString())
                .question2(value.get(5).toString())
                .question2_2(value.get(6).toString())
                .question3(value.get(7).toString())
                .question4(value.get(8).toString())
                .question5(value.get(9).toString())
                .question6(value.get(10).toString())
                .question7(value.get(11).toString())
                .urlList(new UrlList(value.get(12).toString()))
                .lastRowNumber(lastRowNumber)
                .checkA(getCheckValue(value.get(value.size() - 4).toString(), "cto"))
                .checkB(getCheckValue(value.get(value.size() - 3).toString(), "coo"))
                .checkC(getCheckValue(value.get(value.size() - 2).toString(), "cmo"))
                .pageTest(value.get(value.size() - 1).toString())
                .build();
    }

    public ResumeDesign findOneDesigner(String rowId) throws IOException {
        List<List<Object>> values = sheetAPI.getValues(sheetProperties.getDesignerSheetId()
                , getRange(sheetProperties.getDesignerSheetName(), sheetProperties.getStartLow(), sheetProperties.getLastLow())).getValues();
        String lastRowNumber = String.valueOf(values.size() + 1);
        List<Object> value = values.get(Integer.parseInt(rowId) - 2);
        return ResumeDesign.builder()
                .rowId(Long.parseLong(rowId))
                .timestamp(value.get(0).toString())
                .name(value.get(1).toString())
                .email(value.get(2).toString())
                .phoneNumber(value.get(3).toString())
                .question1(value.get(4).toString())
                .question2(value.get(5).toString())
                .question2_2(value.get(6).toString())
                .question3(value.get(7).toString())
                .question4(value.get(8).toString())
                .question5(value.get(9).toString())
                .question6(value.get(10).toString())
                .portfolioLink(value.get(11).toString())
                .lastRowNumber(lastRowNumber)
                .checkA(getCheckValue(value.get(value.size() - 4).toString(), ""))
                .checkB(getCheckValue(value.get(value.size() - 3).toString(), "ceo"))
                .checkC(getCheckValue(value.get(value.size() - 2).toString(), "cdo"))
                .pageTest(value.get(value.size() - 1).toString())
                .build();
    }

    private String getRange(String sheetName, String startRow, String lastRow) {
        return new StringBuilder()
                .append(sheetName)
                .append("!")
                .append(startRow)
                .append(":")
                .append(lastRow)
                .toString();
    }

    private String getCheckValue(String originValue, String checkName) {
        String checkValue = originValue.equals("완료") ? checkName : "";
        return checkValue;
    }
}
