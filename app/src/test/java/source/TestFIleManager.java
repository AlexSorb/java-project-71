package source;

import hexlet.code.source.FileManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;

public class TestFIleManager {

    @Test
    public void testNormalizePath() {
        var notNormalizePath = Paths.get("./src/test/java/resources");
        var normalizeAbsolutePath = Paths.get("src/test/java/resources").toAbsolutePath();
        assertEquals(FileManager.normaolizePath(notNormalizePath), normalizeAbsolutePath);
    }


}
