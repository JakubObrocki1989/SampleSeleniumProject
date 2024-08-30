package org.example.core.models.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserConfig {
    private String user;
    private String username;
    private String email;
    private String password;
}
