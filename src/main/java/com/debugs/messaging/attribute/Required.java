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
public class Required extends Attribute {

    private boolean emptyAllowed;
    private LObject<TBoolean> value;

    public boolean isEmptyAllowed() {
        return emptyAllowed;
    }

    public void setEmptyAllowed(boolean emptyAllowed) {
        this.emptyAllowed = emptyAllowed;
    }

    public LObject<TBoolean> getValue() {
        return value;
    }

    public void setValue(LObject<TBoolean> value) {
        this.value = value;
    }
    
    public Void evaluate(MessageBundle bundle, Field field, TObject value) throws Exception {
        if (evaluateValue(bundle, field, value) &&
                emptyAllowed ? value.getValue() == null : value.size() == 0) {
            throw new RequiredException(this);
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
            return false;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString())
                .append("[emptyAllowed=").append(emptyAllowed)
                .append(", value=").append(value)
                .append(']');
        return sb.toString();
    }
}
