/**
 * Date: 13/11/2018
 */

package io.inhyuck.webservice.controller;

import io.inhyuck.webservice.dto.DesignerResumeResponseDto;
import io.inhyuck.webservice.dto.DeveloperResumeResponseDto;
import io.inhyuck.webservice.dto.ResumeListResponseDto;
import io.inhyuck.webservice.service.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class SheetController {

    @Autowired
    ResumeService resumeService;

    @GetMapping("/list/{role}")
    public String findAll(Model model, @PathVariable("role") String role) throws IOException {
        ResumeListResponseDto resumeListResponseDto = resumeService.findAll(role);
        model.addAttribute("resumeListResponseDto", resumeListResponseDto);
        return "list";
    }

    @GetMapping("/listDetail/{role}")
    public String findAllDetail(Model model, @PathVariable("role") String role) throws IOException {
        ResumeListResponseDto resumeListResponseDto = resumeService.findAll(role);
        model.addAttribute("resumeListResponseDto", resumeListResponseDto);
        return "listDetail";
    }

    @GetMapping("/developer/{rowId}")
    public String findOneDeveloper(Model model, @PathVariable("rowId") String rowId) throws IOException {
        DeveloperResumeResponseDto developerResumeResponseDto= resumeService.findOneDeveloper(rowId);
        model.addAttribute("developerResumeResponseDto", developerResumeResponseDto);
        return "detailDeveloper";
    }

    @GetMapping("/designer/{rowId}")
    public String findOneDesigner(Model model, @PathVariable("rowId") String rowId)  throws IOException {
        DesignerResumeResponseDto designerResumeResponseDto= resumeService.findOneDesigner(rowId);
        model.addAttribute("designerResumeResponseDto", designerResumeResponseDto);
        return "detailDesigner";
    }

}
