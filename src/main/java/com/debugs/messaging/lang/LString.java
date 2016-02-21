/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TObject;
import com.debugs.messaging.type.TString;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LString extends LObject<TString> {
    
    private Object value;

    private LString(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        if (this.value instanceof LObject) {
            TObject evaluatedValue = ((LObject) this.value).evaluate(bundle, field, value);
            return evaluatedValue instanceof TString ?
                    (TString) evaluatedValue :
                    TString.newInstance(evaluatedValue.getValue());
        } else {
            return TString.newInstance(this.value);
        }
    }

    @Override
    public Object[] params() {
        return new Object[] {
            value instanceof LString ?
                value : escape(value.toString())
        };
    }
    
    @Override
    public String toString() {
        return escape(value.toString());
    }
    
    public static LString newInstance(Object value) {
        return new LString(value);
    }
}
