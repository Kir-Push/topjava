package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by buhalo on 23.07.16.
 */
public class CustomDateTimeFormatter implements Formatter<LocalDateTime> {

  private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        LocalDateTime parsedDate = LocalDateTime.parse(text,formatter);
        return parsedDate;
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return object.format(formatter);
    }
}
