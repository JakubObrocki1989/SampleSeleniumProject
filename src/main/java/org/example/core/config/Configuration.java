package org.example.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.core.models.config.BrowserConfig;
import org.example.core.models.config.ConfigFile;
import org.example.core.models.config.Environment;
import org.example.core.models.config.UserConfig;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Slf4j
public class Configuration {
    static boolean instance = false;
    static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    static ConfigFile configuration = loadConfig();


    @SneakyThrows
    private synchronized static ConfigFile loadConfig() {
        try {
            instance = true;
            return mapper.readValue(new File("src/main/resources/config.yaml"), ConfigFile.class);
        } catch (Exception e) {
            log.error("Failed to load configuration file: " + e);
        }
        return null;
    }

    public static BrowserConfig getBrowserConfig() {
        System.setProperty("browser.downloads", configuration.getBrowser().getDownloads());
        return configuration.getBrowser();
    }

    public static String getDownloadPath() {
        return String.valueOf(Path.of(System.getProperty("user.dir"), System.getProperty("browser.downloads")));
    }

    public static Environment getEnvironment() {
        System.setProperty("region", "testing");
        return configuration.getEnvironments().stream().filter(o -> o.getApp().equals(System.getProperty("region"))).collect(Collectors.toList()).get(0);
    }

    public static UserConfig getUsers(String user) {
        return configuration.getUsers().stream().filter(o -> o.getUser().equals(user)).findFirst().get();
    }

    public static ConfigFile getConfiguration() {
        if (instance = true) {
            return configuration;
        } else {
            return loadConfig();
        }
    }


}
