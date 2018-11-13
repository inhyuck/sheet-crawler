package io.inhyuck.webservice.domain.resume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "ResumeDesign{" +
                "\nrowId=" + rowId +
                "\ntimestamp='" + timestamp + '\'' +
                "\nname='" + name + '\'' +
                "\nemail='" + email + '\'' +
                "\nphoneNumber='" + phoneNumber + '\'' +
                "\nquestion1='" + question1 + '\'' +
                "\nquestion2='" + question2 + '\'' +
                "\nquestion2_2='" + question2_2 + '\'' +
                "\nquestion3='" + question3 + '\'' +
                "\nquestion4='" + question4 + '\'' +
                "\nquestion5='" + question5 + '\'' +
                "\nquestion6='" + question6 + '\'' +
                "\nportfolioLink='" + portfolioLink + '\'' +
                '}';
    }
}
