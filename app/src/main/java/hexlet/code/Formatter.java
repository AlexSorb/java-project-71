package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.source.Difference;
import hexlet.code.source.formatters.Json;
import hexlet.code.source.formatters.Plain;
import hexlet.code.source.formatters.Stylish;

import java.util.List;

public class Formatter {

    public static String getOrder(String format, List<Difference> difference) throws IllegalArgumentException,
            JsonProcessingException {
        var normalizeFormat = format.trim().toLowerCase();
        var result = "";

        switch (normalizeFormat) {
            case "plain":
                result += Plain.plain(difference);
                break;
            case "stylish":
                result += Stylish.stylish(difference);
                break;
            case "json":
                result += Json.json(difference);
                break;
            default:
                throw new IllegalArgumentException("Не найден формат");
        }

        return result.toString();
    }
}
