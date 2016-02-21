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
public class LNeq extends LOp<TBoolean, TObject> {
    
    public LNeq(LObject value1, LObject value2) {
        super(value1, value2);
    }

    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedValue1 = getValue1().evaluate(bundle, field, value);
        TObject evaluatedValue2 = getValue2().evaluate(bundle, field, value);
        
        boolean result = !evaluatedValue1.isValueEquals(evaluatedValue2);
        return TBoolean.newInstance(result);
    }
}
