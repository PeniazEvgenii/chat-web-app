package by.it_academy.jd2.util;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DateFormatUtilTest {

    @Test
    @Order(1)
    void parseDateFromString() {
        String date = "1980-12-30";
        LocalDate actualDate = DateFormatUtil.parseDateFromString(date);
        LocalDate expectDate = LocalDate.of(1980, 12, 30);
        Assertions.assertEquals(expectDate, actualDate);
    }

    @Test
    @Order(2)
    void isValidDate() {
        Assertions.assertTrue(DateFormatUtil.isValidDate("2020-01-30"));
        Assertions.assertFalse(DateFormatUtil.isValidDate("2020-30-01"));
        Assertions.assertFalse(DateFormatUtil.isValidDate("30-12-2020"));
    }

    @Test
    @Order(3)
    void getFormatStringDateTime() {
        LocalDateTime time = LocalDateTime.of(2000, 12, 31, 12, 40);
        String actualTime = DateFormatUtil.getFormatStringDateTime(time);
        String expectTime = "12:40:00 31.12.2000";
        Assertions.assertEquals(expectTime, actualTime);
    }
}