/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TInteger;
import com.debugs.messaging.type.TNumber;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LInt extends LObject<TInteger> {
    
    private LObject value;
    private LObject fromBase;
    private LObject toBase;

    public LInt(LObject value) {
        this(value, null);
    }

    public LInt(LObject value, LObject fromBase) {
        this(value, fromBase, null);
    }

    public LInt(LObject value, LObject fromBase, LObject toBase) {
        this.value = value;
        this.fromBase = fromBase;
        this.toBase = toBase;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    public LObject getFromBase() {
        return fromBase;
    }

    public void setFromBase(LObject fromBase) {
        this.fromBase = fromBase;
    }

    public LObject getToBase() {
        return toBase;
    }

    public void setToBase(LObject toBase) {
        this.toBase = toBase;
    }

    @Override
    public TInteger evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        Object resultValue = this.value.evaluate(bundle, field, value).getValue();
        
        if (fromBase != null) {
            TObject evaluatedFromBase = fromBase.evaluate(bundle, field, value);
            TNumber parsedFromBase = evaluatedFromBase instanceof TNumber ?
                    (TNumber) evaluatedFromBase :
                    TInteger.newInstance(evaluatedFromBase.getValue());
            int resultFromBase = parsedFromBase.integerValue();
            
            if (toBase != null) {
                TObject evaluatedToBase = toBase.evaluate(bundle, field, value);
                TNumber parsedToBase = evaluatedToBase instanceof TNumber ?
                        (TNumber) evaluatedToBase :
                        TInteger.newInstance(evaluatedToBase.getValue());
                int resultToBase = parsedToBase.integerValue();
                
                return TInteger.newInstance(resultValue, resultFromBase, resultToBase);
            } else {
                return TInteger.newInstance(resultValue, resultFromBase);
            }
        } else {
            return TInteger.newInstance(resultValue);
        }
    }

    @Override
    public Object[] params() {
        return fromBase == null ?
                new Object[] {value} :
                toBase == null ?
                new Object[] {value, fromBase} :
                new Object[] {value, fromBase, toBase};
    }
}
