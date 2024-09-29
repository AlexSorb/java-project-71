package hexlet.code.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
