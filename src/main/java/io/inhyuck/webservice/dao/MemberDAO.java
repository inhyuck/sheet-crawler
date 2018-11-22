package io.inhyuck.webservice.dao;

import io.inhyuck.webservice.config.MySheetProperties;
import io.inhyuck.webservice.domain.member.Member;
import io.inhyuck.webservice.dao.sheet.SheetAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class MemberDAO {
    @Autowired
    private MySheetProperties sheetProperties;
    @Autowired
    private SheetAPI sheetAPI;

    public Member findByUsernameAndPassword(String username, String password) throws IOException {
        String range = getRange(sheetProperties.getMemberInfoSheetName()
                , sheetProperties.getStartLow()
                , sheetProperties.getLastLow());
        List<List<Object>> values = sheetAPI.getValues(sheetProperties.getMemberInfoSheetId(), range).getValues();

        for (List<Object> value : values) {
            if (value.get(0).equals(username) && value.get(1).equals(password)) {
                return Member.builder()
                        .username(username)
                        .password(password)
                        .fullname(value.get(2).toString())
                        .build();
            }
        }
        return null;
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
