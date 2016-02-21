/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TBoolean;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LMatch extends LObject<TBoolean> {
    
    private LObject value;
    private LObject pattern;

    public LMatch(LObject value, LObject pattern) {
        this.value = value;
        this.pattern = pattern;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    public LObject getPattern() {
        return pattern;
    }

    public void setPattern(LObject pattern) {
        this.pattern = pattern;
    }

    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String resultValue = this.value.evaluate(bundle, field, value).toString();
        String resultPattern = pattern.evaluate(bundle, field, value).toString();
        
        boolean result = resultValue.matches(resultPattern);
        return TBoolean.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value, pattern};
    }
}
