package io.inhyuck.webservice.domain.resume;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeSimple {
    private String rowId;
    private String timestamp;
    private String name;
    private String email;
    private String phoneNumber;
    private String question1;
    private String question2;
    private String question2_2;
}
