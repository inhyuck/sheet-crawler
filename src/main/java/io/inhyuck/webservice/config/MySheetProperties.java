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
    private String developer;
    private String designer;
    private String memberInfo;
    private String sheetName;
    private String startLow;
    private String lastLow;
}
