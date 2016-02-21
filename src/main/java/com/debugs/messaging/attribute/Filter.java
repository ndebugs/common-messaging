/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.attribute;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TBoolean;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class Filter extends Attribute {
    
    private boolean error;
    private LObject<TBoolean> value;

    public LObject<TBoolean> getValue() {
        return value;
    }

    public void setValue(LObject<TBoolean> value) {
        this.value = value;
    }
    
    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Void evaluate(MessageBundle bundle, Field field, TObject value) throws Exception {
        if (!evaluateValue(bundle, field, value)) {
            if (error) {
                throw new FilterMismatchException(this, value);
            } else {
                value.setValue(null);
            }
        }
        return null;
    }

    private boolean evaluateValue(MessageBundle bundle, Field field, TObject value) throws LangErrorException {
        if (this.value != null) {
            Object typeValue = value.getValue();
            try {
                TBoolean evaluatedValue = this.value.evaluate(bundle, field, typeValue);
                return evaluatedValue.getValue();
            } catch (Exception e) {
                throw new LangErrorException(this, typeValue, e);
            }
        } else {
            return true;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString())
                .append("[error=").append(error)
                .append(", value=").append(value)
                .append(']');
        return sb.toString();
    }
}
