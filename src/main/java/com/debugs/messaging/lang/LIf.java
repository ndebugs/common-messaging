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
public class LIf extends LObject {
    
    private LObject<TBoolean> condition;
    private LObject trueValue;
    private LObject falseValue;

    public LIf(LObject<TBoolean> condition, LObject trueValue, LObject falseValue) {
        this.condition = condition;
        this.trueValue = trueValue;
        this.falseValue = falseValue;
    }

    public LObject<TBoolean> getCondition() {
        return condition;
    }

    public void setCondition(LObject<TBoolean> condition) {
        this.condition = condition;
    }

    public LObject getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(LObject trueValue) {
        this.trueValue = trueValue;
    }

    public LObject getFalseValue() {
        return falseValue;
    }

    public void setFalseValue(LObject falseValue) {
        this.falseValue = falseValue;
    }

    @Override
    public TObject evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TBoolean evaluatedCondition = condition.evaluate(bundle, field, value);
        
        return evaluatedCondition.getValue() ?
                trueValue.evaluate(bundle, field, value) :
                falseValue.evaluate(bundle, field, value);
    }

    @Override
    public Object[] params() {
        return new Object[] {condition, trueValue, falseValue};
    }
}
