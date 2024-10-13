package by.it_academy.jd2.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFormatUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TIME = "HH:mm:ss dd.MM.yyyy";

    private DateFormatUtil() {
    }

    public static LocalDate parseDateFromString (String rawDate) {
        return LocalDate.parse(rawDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static boolean isValidDate(String rawDate) {
        try {
            parseDateFromString (rawDate);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public static String getFormatStringDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(FORMAT_DATE_TIME));

    }
}
