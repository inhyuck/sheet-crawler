package io.inhyuck.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Card {
    private String questionText;
    private String answerText;
}
