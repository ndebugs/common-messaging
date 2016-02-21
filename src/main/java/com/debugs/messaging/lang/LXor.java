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
public class LXor extends LOp<TBoolean, TBoolean> {
    
    public LXor(LObject<TBoolean> value1, LObject<TBoolean> value2) {
        super(value1, value2);
    }

    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TBoolean evaluatedValue1 = getValue1().evaluate(bundle, field, value);
        TBoolean evaluatedValue2 = getValue2().evaluate(bundle, field, value);
        
        boolean result = evaluatedValue1.getValue() ^ evaluatedValue2.getValue();
        return TBoolean.newInstance(result);
    }
}
