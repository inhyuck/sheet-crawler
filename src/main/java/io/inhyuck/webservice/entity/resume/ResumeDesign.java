package io.inhyuck.webservice.entity.resume;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeDesign {
    private Long rowId;
    private String timestamp;
    private String name;
    private String email;
    private String phoneNumber;
    private String question1;
    private String question2;
    private String question2_2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String portfolioLink;
    private String lastRowNumber;
    private String checkA;
    private String checkB;
    private String checkC;
    private String pageTest; //서류합격 여부 o, x
}
