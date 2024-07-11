package by.modsen.practice.group11.utils;

import by.modsen.practice.group11.model.enums.LinePattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class StringFormatMatcher {
    private final Map<LinePattern, Pattern> patterns = new HashMap<LinePattern, Pattern>() {
        {
            put(LinePattern.EMAIL, Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.([a-zA-Z0-9_-])+)*$"));
            put(LinePattern.LOGIN, Pattern.compile("[a-zA-Z0-9_-]+"));
        }
    };

    public LinePattern isLoginOrUsername(String line) {

        for (Map.Entry<LinePattern, Pattern> pair : patterns.entrySet()) {
            if (pair.getValue().matcher(line).matches()) {
                return pair.getKey();
            };
        }

        return LinePattern.ANOTHER;
    }
}
