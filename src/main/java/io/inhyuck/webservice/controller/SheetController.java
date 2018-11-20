/**
 * Date: 13/11/2018
 */

package io.inhyuck.webservice.controller;

import io.inhyuck.webservice.domain.resume.ResumeDesign;
import io.inhyuck.webservice.domain.resume.ResumeDevelop;
import io.inhyuck.webservice.domain.resume.ResumeMini;
import io.inhyuck.webservice.service.sheet.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class SheetController {

    @Autowired
    ResumeService resumeService;

    @GetMapping("/list/{role}")
    public String findAll(Model model, @PathVariable("role") String role) throws IOException {
        List<ResumeMini> resumeList = resumeService.findAll(role);
        model.addAttribute("role", role);
        model.addAttribute("resumeList", resumeList);
        return "list";
    }

    @GetMapping("/developer/{rowId}")
    public String findOneDeveloper(Model model, @PathVariable("rowId") String rowId) throws IOException {
        ResumeDevelop resumeDevelop = resumeService.findOneDeveloper(rowId);
        model.addAttribute("role", "developer");
        model.addAttribute("resumeDevelop", resumeDevelop);
        return "detailDeveloper";
    }

    @GetMapping("/designer/{rowId}")
    public String findOneDesigner(Model model, @PathVariable("rowId") String rowId)  throws IOException {
        ResumeDesign resumeDesign = resumeService.findOneDesigner(rowId);
        model.addAttribute("role", "designer");
        model.addAttribute("resumeDesign", resumeDesign);
        return "detailDesinger";
    }

}
