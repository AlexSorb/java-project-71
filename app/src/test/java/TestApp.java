import hexlet.code.source.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

public class TestApp {
    public static Map<String, Object> firstJson;
    public static Map<String, Object> secondJson;

    @BeforeAll
    public static void generate() {
        firstJson = Map.of(
                "host", "hexlet.io",
                "timeout", "50",
                "proxy", "123.234.53.22",
                "follow", "false"
        );

        secondJson = Map.of(
                "timeout", "20",
                "verbose", "true",
                "host", "hexlet.io"
        );
    }
    @Test
    public void testParsing() {
        System.out.println("Test1");
        String rightAnswer = "{\n" +
                "- follow: false\n" +
                "  host: hexlet.io\n" +
                "- proxy: 123.234.53.22\n" +
                "- timeout: 50\n" +
                "+ timeout: 20\n" +
                "+ verbose: true\n" +
                "}";
        var result = Differ.generate(firstJson, secondJson);
        assertEquals(result, rightAnswer);
    }

}
