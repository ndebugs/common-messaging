/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TBoolean;
import com.debugs.messaging.type.TDecimal;
import com.debugs.messaging.type.TNumber;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LBetween extends LObject<TBoolean> {
    
    private LObject value;
    private LObject min;
    private LObject max;

    public LBetween(LObject value, LObject min, LObject max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    public LObject getMin() {
        return min;
    }

    public void setMin(LObject min) {
        this.min = min;
    }

    public LObject getMax() {
        return max;
    }

    public void setMax(LObject max) {
        this.max = max;
    }
    
    protected Boolean execute(int value, int min, int max) {
        return value >= min && value <= max;
    }

    protected Boolean execute(double value, double min, double max) {
        return value >= min && value <= max;
    }
    
    protected Boolean execute(TNumber value, TNumber min, TNumber max) {
        if (value instanceof TDecimal || min instanceof TDecimal || max instanceof TDecimal) {
            return execute(value.decimalValue(), min.decimalValue(), max.decimalValue());
        } else {
            return execute(value.integerValue(), min.integerValue(), max.integerValue());
        }
    }
    
    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedValue = this.value.evaluate(bundle, field, value);
        TNumber parsedValue = evaluatedValue instanceof TNumber ?
                (TNumber) evaluatedValue :
                TNumber.newInstance(evaluatedValue.getValue());
        
        TObject evaluatedMin = min.evaluate(bundle, field, value);
        TNumber parsedMin = evaluatedMin instanceof TNumber ?
                (TNumber) evaluatedMin :
                TNumber.newInstance(evaluatedMin.getValue());
        
        TObject evaluatedMax = max.evaluate(bundle, field, value);
        TNumber parsedMax = evaluatedMax instanceof TNumber ?
                (TNumber) evaluatedMax :
                TNumber.newInstance(evaluatedMax.getValue());
        
        boolean result = execute(parsedValue, parsedMin, parsedMax);
        return TBoolean.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value, min, max};
    }
}
