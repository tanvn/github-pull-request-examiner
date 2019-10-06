package tanvn.github.api.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ResultWriter {
    private static final Logger logger = LoggerFactory.getLogger(ResultWriter.class);


    private final static String resultPath = "result/result.txt"; //TODO replace with system property
    public static void write(List<String> result) {
        Path file = Paths.get(resultPath).toAbsolutePath();
        try {
            Files.deleteIfExists(file);
            logger.debug("write result to {}", file);
            Files.createFile(file);
            Files.write(file, result, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("write result error", e);
        }
    }
}
