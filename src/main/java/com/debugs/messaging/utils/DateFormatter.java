/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class DateFormatter {
    
    public static final char SECOND = 's';
    public static final char MINUTE = 'm';
    public static final char HOUR = 'h';
    public static final char DAY = 'D';
    public static final char MONTH = 'M';
    public static final char YEAR = 'Y';
    
    private final String pattern;

    public DateFormatter(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
    
    public int toCalendarField(char unit) {
        switch (unit) {
            case SECOND: return Calendar.SECOND;
            case MINUTE: return Calendar.MINUTE;
            case HOUR: return Calendar.HOUR;
            case DAY: return Calendar.DATE;
            case MONTH: return Calendar.MONTH;
            case YEAR: return Calendar.YEAR;
        }
        return 0;
    }
    
    public String format(Calendar calendar, String difference) {
        int lastIndex = difference.length() - 1;
        int value = Integer.parseInt(difference.substring(0, lastIndex));
        char unit = difference.charAt(lastIndex);
        return format(calendar, value, unit);
    }
    
    public String format(Calendar calendar, int diffValue, char diffUnit) {
        calendar.add(toCalendarField(diffUnit), diffValue);
        return format(calendar.getTime());
    }
    
    public String format(Date date) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}
