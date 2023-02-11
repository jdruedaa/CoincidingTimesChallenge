package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleUtils {
    public static List<String> getKeysAndCountedTimes(String answer)
    {
        List<String> keysAndCountedTimes = Arrays.stream(answer.split("\n"))
                .flatMap(line -> Arrays.stream(line.split(":")))
                .collect(Collectors.toList());
        return keysAndCountedTimes;
    }
}
