/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.map;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TInteger;
import com.debugs.messaging.type.TMap;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LSize extends LObject<TInteger> {
    
    private LObject<TMap> value;

    public LSize(LObject<TMap> value) {
        this.value = value;
    }

    public LObject<TMap> getValue() {
        return value;
    }

    public void setValue(LObject<TMap> value) {
        this.value = value;
    }

    @Override
    public TInteger evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TMap evaluatedValue = this.value.evaluate(bundle, field, value);
        
        int result = evaluatedValue.size();
        return TInteger.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
