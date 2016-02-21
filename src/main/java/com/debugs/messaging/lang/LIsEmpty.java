/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TBoolean;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LIsEmpty extends LObject<TBoolean> {
    
    private LObject value;

    public LIsEmpty(LObject value) {
        this.value = value;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject resultValue = this.value.evaluate(bundle, field, value);
        
        boolean result = resultValue.size() == 0;
        return TBoolean.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
