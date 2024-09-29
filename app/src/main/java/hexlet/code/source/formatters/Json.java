package hexlet.code.source.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.source.Difference;

import java.util.List;

public class Json {
    public static String json(List<Difference> data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var result = objectMapper.writeValueAsString(data);
        return result.trim();
    }
}
