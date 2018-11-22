package io.inhyuck.webservice.service;

import io.inhyuck.webservice.config.MySheetProperties;
import io.inhyuck.webservice.dao.ResumeDAO;
import io.inhyuck.webservice.domain.resume.ResumeDesign;
import io.inhyuck.webservice.domain.resume.ResumeDevelop;
import io.inhyuck.webservice.dto.DesignerResumeResponseDto;
import io.inhyuck.webservice.dto.DeveloperResumeResponseDto;
import io.inhyuck.webservice.dto.ResumeListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResumeService {
    @Autowired
    MySheetProperties sheetProperties;
    @Autowired
    ResumeDAO resumeDAO;

    static final String DEVELOPER = "developer";
    static final String DESIGNER = "designer";

    public ResumeListResponseDto findAll(String role) throws IOException {
        return ResumeListResponseDto.builder()
                .resumeSimpleList(resumeDAO.findAll(role))
                .role(role)
                .build();
    }

    public DeveloperResumeResponseDto findOneDeveloper(String rowId) throws IOException {
        ResumeDevelop resumeDevelop = resumeDAO.findOneDeveloper(rowId);
        return DeveloperResumeResponseDto.builder()
                .resumeDevelop(resumeDevelop)
                .role(DEVELOPER)
                .rowId(rowId)
                .preRowId((rowId.equals("2")) ? "" : String.valueOf(Integer.parseInt(rowId) - 1))
                .nextRowId(rowId.equals(resumeDevelop.getLastRowNumber()) ? "" : String.valueOf(Integer.parseInt(rowId) + 1))
                .build();
    }

    public DesignerResumeResponseDto findOneDesigner(String rowId) throws IOException {
        ResumeDesign resumeDesign = resumeDAO.findOneDesigner(rowId);
        return DesignerResumeResponseDto.builder()
                .resumeDesign(resumeDesign)
                .role(DESIGNER)
                .rowId(rowId)
                .preRowId((rowId.equals("2")) ? "" : String.valueOf(Integer.parseInt(rowId) - 1))
                .nextRowId(rowId.equals(resumeDesign.getLastRowNumber()) ? "" : String.valueOf(Integer.parseInt(rowId) + 1))
                .build();
    }
}
