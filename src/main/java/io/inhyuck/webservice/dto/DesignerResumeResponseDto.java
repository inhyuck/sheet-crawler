package io.inhyuck.webservice.dto;

import io.inhyuck.webservice.entity.resume.ResumeDesign;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DesignerResumeResponseDto {
    private ResumeDesign resumeDesign;
    private List<Card> cardList;
    private String role;
    private String rowId;
    private String preRowId;
    private String nextRowId;
}
