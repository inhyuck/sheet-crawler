package io.inhyuck.webservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "card")
public class CardProperties {
    private List<String> developerQuestions;
    private List<String> designerQuestions;
}
