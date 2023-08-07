package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        TimeZone timeZoneWarsaw = TimeZone.getTimeZone("Poland/Warsaw");
        TimeZone timeZoneDhaka = TimeZone.getTimeZone("Asia/Dhaka");


        localDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));

        System.out.println("LocalTime: " + localDateTime.atZone(timeZoneDhaka.toZoneId()));
    }
}