package org.example.core.models.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigFile {
    private String appName;
    private String region;
    private List<Environment> environments;
    private BrowserConfig browser;
    private List<UserConfig> users;
}
