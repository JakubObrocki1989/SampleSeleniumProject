package org.example.core.models.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Environment {
    private String app;
    private String appUrl;
    private String apiUrl;
}
