/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TBoolean;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LNot extends LObject<TBoolean> {
    
    private LObject<TBoolean> value;

    public LNot(LObject<TBoolean> value) {
        this.value = value;
    }

    public LObject<TBoolean> getValue() {
        return value;
    }

    public void setValue(LObject<TBoolean> value) {
        this.value = value;
    }

    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TBoolean evaluatedValue = this.value.evaluate(bundle, field, value);
        
        boolean result = !evaluatedValue.getValue();
        return TBoolean.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
