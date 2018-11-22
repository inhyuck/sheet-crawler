package io.inhyuck.webservice.service;

import io.inhyuck.webservice.config.MySheetProperties;
import io.inhyuck.webservice.dao.ResumeDAO;
import io.inhyuck.webservice.domain.resume.ResumeDesign;
import io.inhyuck.webservice.domain.resume.ResumeDevelop;
import io.inhyuck.webservice.domain.resume.ResumeSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ResumeService {
    @Autowired
    MySheetProperties sheetProperties;
    @Autowired
    ResumeDAO resumeDAO;

    public List<ResumeSimple> findAll(String role) throws IOException {
        List<ResumeSimple> resumeList = resumeDAO.findAll(role);
        return resumeList;
    }

    public ResumeDesign findOneDesigner(String rowId) throws IOException {
        return resumeDAO.findOneDesigner(rowId);
    }

    public ResumeDevelop findOneDeveloper(String rowId) throws IOException {
        return resumeDAO.findOneDeveloper(rowId);
    }
}
