/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TCharacter;
import com.debugs.messaging.type.TInteger;
import com.debugs.messaging.type.TNumber;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LChar extends LObject<TCharacter> {
    
    private LObject value;

    public LChar(LObject value) {
        this.value = value;
    }

    public LObject<TInteger> getValue() {
        return value;
    }

    public void setValue(LObject<TInteger> value) {
        this.value = value;
    }

    @Override
    public TCharacter evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedValue = this.value.evaluate(bundle, field, value);
        TNumber parsedValue = evaluatedValue instanceof TNumber ?
                (TNumber) evaluatedValue :
                TInteger.newInstance(evaluatedValue.getValue());
        
        Character result = (char) parsedValue.integerValue();
        return TCharacter.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
