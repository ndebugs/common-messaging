/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class TNumber<T extends Number> extends TObject<T> {

    public TNumber() {}

    public abstract double decimalValue();
    
    public abstract int integerValue();
    
    @Override
    protected Object[] params() {
        return null;
    }

    public static TNumber newInstance(Object value) throws TypeMismatchException {
        if (value == null || value instanceof Integer) {
            return TInteger.newInstance(value);
        } else if (value instanceof Double) {
            return TDecimal.newInstance(value);
        } else {
            String tempValue = value.toString();
            return tempValue.indexOf('.') > -1 ?
                    TDecimal.newInstance(tempValue) :
                    TInteger.newInstance(value);
        }
    }
}
