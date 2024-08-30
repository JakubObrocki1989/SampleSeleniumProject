package org.example.core.models.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrowserConfig {
    private String name;
    private Boolean headless;
    private Boolean ignoreSSL;
    private String size;
    private String downloads;
    private WaitConfig webElement;
    private Integer maxAppLoadingTimeInMinutes;
}
