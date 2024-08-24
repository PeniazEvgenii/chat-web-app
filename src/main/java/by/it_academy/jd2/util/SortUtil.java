package by.it_academy.jd2.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class SortUtil {
    private SortUtil() {}

    public static List<Map.Entry<Long, Long>> sort(Map<Long, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }





}
