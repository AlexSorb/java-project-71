package hexlet.code.source.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String json(Map<String, List<Object>> differenceMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var result = objectMapper.writeValueAsString(differenceMap);
        return result.trim();
    }
}
