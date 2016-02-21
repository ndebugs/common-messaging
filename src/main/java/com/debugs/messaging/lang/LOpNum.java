/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TDecimal;
import com.debugs.messaging.type.TNumber;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class LOpNum<T extends TObject> extends LOp<T, TObject> {

    public LOpNum(LObject value1, LObject value2) {
        super(value1, value2);
    }
    
    protected abstract T execute(int value1, int value2) throws Exception;

    protected abstract T execute(double value1, double value2) throws Exception;
    
    @Override
    public T evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedValue1 = getValue1().evaluate(bundle, field, value);
        TNumber parsedValue1 = evaluatedValue1 instanceof TNumber ?
                (TNumber) evaluatedValue1 :
                TNumber.newInstance(evaluatedValue1.getValue());
        
        TObject evaluatedValue2 = getValue2().evaluate(bundle, field, value);
        TNumber parsedValue2 = evaluatedValue2 instanceof TNumber ?
                (TNumber) evaluatedValue2 :
                TNumber.newInstance(evaluatedValue2.getValue());
        
        if (parsedValue1 instanceof TDecimal || parsedValue2 instanceof TDecimal) {
            return execute(parsedValue1.decimalValue(), parsedValue2.decimalValue());
        } else {
            return execute(parsedValue1.integerValue(), parsedValue2.integerValue());
        }
    }
}
