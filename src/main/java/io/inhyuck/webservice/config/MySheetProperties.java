package io.inhyuck.webservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sheet")
public class MySheetProperties {
    private String developerSheetId;
    private String designerSheetId;
    private String memberInfoSheetId;
    private String startLow;
    private String lastLow;
    private String developerSheetName;
    private String designerSheetName;
    private String memberInfoSheetName;
    private String jsonLocation;
}
