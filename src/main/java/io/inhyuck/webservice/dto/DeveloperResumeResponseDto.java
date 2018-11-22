package io.inhyuck.webservice.dto;

import io.inhyuck.webservice.domain.resume.ResumeDevelop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeveloperResumeResponseDto {
    private ResumeDevelop resumeDevelop;
    private String role;
    private String rowId;
    private String preRowId;
    private String nextRowId;
}
