package org.example.core.helpers;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.example.core.config.Configuration;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;

public class FileHandler {

    @SneakyThrows
    public String readFile(String fileName) {
        return Files.readString(Path.of(Configuration.getDownloadPath() + "\\" + fileName), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static void waitForDownload(String fileName) {
        Path path = Path.of(Configuration.getDownloadPath() + "\\" + fileName);
        await()
                .atMost(Duration.ofMinutes(1))
                .ignoreExceptions()
                .until(() -> path.toFile().exists());
    }

    @SneakyThrows
    public void renameFile(String oldName, String newName) {
        FileUtils.moveFile(FileUtils.getFile(oldName), FileUtils.getFile(newName));
    }

    @SneakyThrows
    public Set<String> listFiles(String dir) {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public void deleteFiles(String path) {
        Set<String> files = listFiles(path);
        files.forEach(o -> {
            FileUtils.deleteQuietly(FileUtils.getFile(path + "/" + o));
        });
    }
}
