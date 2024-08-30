package org.example.core.models.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig {
    private String app;
    private String appUrl;
    private String apiUrl;
//    private HashMap<String, UserConfig> users;
}
