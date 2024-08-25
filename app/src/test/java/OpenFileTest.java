import hexlet.code.source.FileManager;
import org.junit.jupiter.api.Test;


import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenFileTest {

    @Test
    public void testOpenFile() {
        String relativePath = "/src/test/resurce/File1";
        var absolutePath = new File(relativePath).getAbsolutePath();
        assertEquals(FileManager.normaolizePath(relativePath).toString(), absolutePath.toString());
    }
}
