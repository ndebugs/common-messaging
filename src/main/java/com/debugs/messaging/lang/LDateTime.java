/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TObject;
import com.debugs.messaging.type.TString;
import com.debugs.messaging.utils.DateFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LDateTime extends LObject<TString> {
    
    private LObject pattern;
    private LObject difference;

    public LDateTime(LObject pattern) {
        this(pattern, null);
    }

    public LDateTime(LObject pattern, LObject difference) {
        this.pattern = pattern;
        this.difference = difference;
    }

    public LObject getPattern() {
        return pattern;
    }

    public void setPattern(LObject pattern) {
        this.pattern = pattern;
    }

    public LObject getDifference() {
        return difference;
    }

    public void setDifference(LObject difference) {
        this.difference = difference;
    }

    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedFormat = pattern.evaluate(bundle, field, value);
        
        String result;
        DateFormatter formatter = new DateFormatter(evaluatedFormat.toString());
        if (difference != null) {
            TObject evaluatedDifference = difference.evaluate(bundle, field, value);
            result = formatter.format(Calendar.getInstance(), evaluatedDifference.toString());
        } else {
            result = formatter.format(new Date());
        }
        return TString.newInstance(result);
    }

    @Override
    public Object[] params() {
        return difference == null ?
                new Object[] {pattern} :
                new Object[] {pattern, difference};
    }
}
