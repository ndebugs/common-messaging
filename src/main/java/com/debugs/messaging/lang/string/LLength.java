/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TInteger;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LLength extends LObject<TInteger> {
    
    private LObject value;

    public LLength(LObject value) {
        this.value = value;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    @Override
    public TInteger evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String resultValue = this.value.evaluate(bundle, field, value).toString();
        
        int result = resultValue.length();
        return TInteger.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
