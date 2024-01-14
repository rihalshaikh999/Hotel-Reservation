package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TestUtils {
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/[a-zA-Z]{3}/\\d{4}");

    public static Date parseDate(String dateString) {
        Matcher matcher = DATE_PATTERN.matcher(dateString);
        if (matcher.matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
            try {
                return sdf.parse(dateString);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format: " + dateString);
            }
        } else {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }
}
