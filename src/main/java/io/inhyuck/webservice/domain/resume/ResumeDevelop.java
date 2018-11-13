package io.inhyuck.webservice.domain.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ResumeDevelop {
    private Long rowId;
    private String timestamp;
    private String name;
    private String email;
    private String phoneNumber;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
}
