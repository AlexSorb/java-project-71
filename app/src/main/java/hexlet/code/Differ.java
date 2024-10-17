package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


public class Differ {
    public final static String DEFAULT_FORMAT = "stylish";

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var normalizedPath1 = FileManager.normaolizePath(Path.of(filePath1));
        var normalizedPath2 = FileManager.normaolizePath(Path.of(filePath2));


        if (Files.notExists(normalizedPath1) || Files.notExists(normalizedPath2)) {
            throw new FileNotFoundException("Файл для чтения не найден");
        }

        ObjectMapper mapper = (FileManager.isJsonFile(normalizedPath1)) ? new ObjectMapper() : new YAMLMapper();
        var dataFirst = mapper.readValue(normalizedPath1.toFile(), Map.class);
        var dataSecond = mapper.readValue(normalizedPath2.toFile(), Map.class);

        var differenceMap = TreeBuilder.getTreeDifference(dataFirst, dataSecond);

        return Formatter.generateFormatString(format, differenceMap);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return Differ.generate(filePath1, filePath2, DEFAULT_FORMAT);
    }
}
