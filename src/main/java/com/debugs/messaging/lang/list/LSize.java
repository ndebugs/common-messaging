/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.list;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TInteger;
import com.debugs.messaging.type.TList;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LSize extends LObject<TInteger> {
    
    private LObject<TList> value;

    public LSize(LObject<TList> value) {
        this.value = value;
    }

    public LObject<TList> getValue() {
        return value;
    }

    public void setValue(LObject<TList> value) {
        this.value = value;
    }

    @Override
    public TInteger evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TList evaluatedValue = this.value.evaluate(bundle, field, value);
        
        int result = evaluatedValue.size();
        return TInteger.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
