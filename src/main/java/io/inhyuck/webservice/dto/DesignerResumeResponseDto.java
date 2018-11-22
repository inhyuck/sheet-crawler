package io.inhyuck.webservice.dto;

import io.inhyuck.webservice.domain.resume.ResumeDesign;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DesignerResumeResponseDto {
    private ResumeDesign resumeDesign;
    private String role;
    private String rowId;
    private String preRowId;
    private String nextRowId;
}
