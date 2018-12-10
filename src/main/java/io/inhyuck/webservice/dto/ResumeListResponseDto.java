package io.inhyuck.webservice.dto;

import io.inhyuck.webservice.entity.resume.ResumeSimple;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResumeListResponseDto {
    private List<ResumeSimple> resumeSimpleList;
    private String role;
}
