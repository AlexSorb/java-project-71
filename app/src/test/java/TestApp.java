import hexlet.code.source.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class TestApp {
    public static Map<String, String> firstJson;
    public static Map<String, String> secondJson;

    @BeforeAll
    public static void generate(){
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
    public void testApp() {
        System.out.println("Test1");
        StringBuilder rightAnser = new StringBuilder("{\n")
                .append( "- follow: false\n")
                .append("host: hexlet.io\n")
                .append("- proxy: 123.234.53.22\n")
                .append("- timeout: 50\n")
                .append("+ timeout: 20\n")
                .append("+ verbose: true\n");
        var result = Differ.generate(firstJson, secondJson);
        assertEquals(result, rightAnser);
    }

}
