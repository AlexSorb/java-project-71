package hexlet.code.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Difference {
    private String key;
    private Object oldValue;
    private Object newValue;
    private String state;



    @Override
    public String toString() {
        return "Difference{"
                + "key='" + key + '\''
                + ", oldValue=" + oldValue
                + ", newValue=" + newValue
                + '}';
    }
}
