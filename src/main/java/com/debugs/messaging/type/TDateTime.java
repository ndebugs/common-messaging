/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TDateTime extends TString {

    private final String format;

    public TDateTime(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
    
    public boolean isEmpty(String value) {
        for (char c : value.toCharArray()) {
            if (c != '0') {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String parse(Object value) throws TypeMismatchException {
        if (value != null) {
            String resultValue = value.toString();
            boolean valid;
            if (isEmpty(resultValue)) {
                valid = true;
            } else {
                Date date = null;
                try {
                    DateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
                    date = formatter.parse(resultValue);
                } catch (ParseException ex) {}
                valid = date != null;
            }
            if (valid) {
                return resultValue;
            } else {
                throw new TypeMismatchException(this, value);
            }
        } else {
            return null;
        }
    }

    @Override
    protected Object[] params() {
        return new Object[] {format};
    }
}
