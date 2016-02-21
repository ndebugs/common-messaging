/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TDecimal extends TNumber<Double> {

    public TDecimal() {}

    private TDecimal(Object value) throws TypeMismatchException {
        Double parsedValue = parse(value);
        setValue(parsedValue);
    }

    @Override
    public double decimalValue() {
        Double value = getValue();
        return value != null ? value : 0;
    }

    @Override
    public int integerValue() {
        Double value = getValue();
        return value != null ? value.intValue() : 0;
    }
    
    public Double parse(Object value) throws TypeMismatchException {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else {
            try {
                return Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                throw new TypeMismatchException(this, value);
            }
        }
    }

    @Override
    public TObject clone(Object value) throws TypeMismatchException {
        return newInstance(value);
    }
    
    @Override
    public int size() {
        Double value = getValue();
        return value != null ? (int) Math.ceil(value) : null;
    }
    
    @Override
    public String toString() {
        Double value = getValue();
        return value != null ? value.toString() : "";
    }
    
    public static TDecimal newInstance(Object value) throws TypeMismatchException {
        return new TDecimal(value);
    }
}
