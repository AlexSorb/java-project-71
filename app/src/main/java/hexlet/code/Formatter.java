package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String generateFormatString(String format, Map<String, List<Object>> differenceMap)
            throws IllegalArgumentException, JsonProcessingException {
        var normalizeFormat = format.trim().toLowerCase();
        var result = "";

        switch (normalizeFormat) {
            case "plain":
                result += Plain.plain(differenceMap);
                break;
            case "stylish":
                result += Stylish.stylish(differenceMap);
                break;
            case "json":
                result += Json.json(differenceMap);
                break;
            default:
                throw new IllegalArgumentException("Не найден формат");
        }

        return result;
    }
}
