/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TDecimal;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LDec extends LObject<TDecimal> {
    
    private LObject value;

    public LDec(LObject value) {
        this.value = value;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }
    
    @Override
    public TDecimal evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedValue = this.value.evaluate(bundle, field, value);
        
        return evaluatedValue instanceof TDecimal ?
                (TDecimal) evaluatedValue : TDecimal.newInstance(evaluatedValue.getValue());
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
