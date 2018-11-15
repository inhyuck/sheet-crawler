/**
 * Date: 13/11/2018
 * Author: inhyuck | https://github.com/inhyuck
 * Solution URL: https://github.com/inhyuck/algorithm
 * Title:
 * Problem:
 * URL: https://www.acmicpc.net/problem/
 */

package io.inhyuck.webservice.service.sheet;

import io.inhyuck.webservice.config.MySheetProperties;
import io.inhyuck.webservice.domain.resume.ResumeDesign;
import io.inhyuck.webservice.domain.resume.ResumeDevelop;
import io.inhyuck.webservice.domain.resume.ResumeMini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeService {
    @Autowired
    MySheetProperties sheetProperties;
    @Autowired
    SheetAPI sheetAPI;

    public List<ResumeMini> findAll(String role) throws IOException {
        List<List<Object>> values;
        if (role.equals("developer")) {
            values = sheetAPI.findAll(sheetProperties.getDeveloper());
        } else {
            values = sheetAPI.findAll(sheetProperties.getDesigner());
        }

        List<ResumeMini> resumeList = new ArrayList<>();
        ResumeMini resumeMini;
        int rowId = 2;
        for (List<Object> value : values) {
            resumeMini = ResumeMini.builder()
                    .rowId(String.valueOf(rowId++))
                    .timestamp(value.get(0).toString())
                    .name(value.get(1).toString())
                    .email(value.get(2).toString())
                    .phoneNumber((value.get(3).toString()))
                    .question1(value.get(4).toString())
                    .question2(value.get(5).toString())
                    .question2_2(value.get(6).toString())
                    .build();
            resumeList.add(resumeMini);
        }
        return resumeList;
    }

    public ResumeDesign findOneDesinger(String rowId) throws IOException {
        List<Object> values = sheetAPI.findOne(sheetProperties.getDesigner(), rowId);
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
        List<Object> values = sheetAPI.findOne(sheetProperties.getDeveloper(), rowId);
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
                .question8(values.get(12).toString())
                .build();
    }
}
