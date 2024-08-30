package org.example.core.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.core.config.Configuration;

import java.io.File;
import java.io.IOException;

public class ObjectHandler {
    File file = new File("src/main/resources/config.yaml");
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    Configuration config = objectMapper.readValue(file, Configuration.class);

    public ObjectHandler() throws IOException {
    }

    public Configuration getConfig() {
        return config;
    }

}
