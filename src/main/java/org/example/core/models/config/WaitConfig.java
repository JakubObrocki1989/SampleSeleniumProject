package org.example.core.models.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WaitConfig {
    private Integer timeout;
    private Integer polling;
}
