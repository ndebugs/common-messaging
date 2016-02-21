/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TInteger extends TNumber<Integer> {

    private final int fromBase;
    private final int toBase;
    
    public TInteger() {
        this.fromBase = 10;
        this.toBase = 10;
    }

    private TInteger(Object value, int fromBase, int toBase) throws TypeMismatchException {
        this.fromBase = fromBase;
        this.toBase = toBase;
        
        Integer parsedValue = parse(value, fromBase);
        setValue(parsedValue);
    }

    public int getFromBase() {
        return fromBase;
    }

    public int getToBase() {
        return toBase;
    }

    @Override
    public double decimalValue() {
        Integer value = getValue();
        return value != null ? value.doubleValue() : 0;
    }

    @Override
    public int integerValue() {
        Integer value = getValue();
        return value != null ? value : 0;
    }

    public Integer parse(Object value) throws TypeMismatchException {
        return parse(value, 10);
    }

    public Integer parse(Object value, int base) throws TypeMismatchException {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else {
            try {
                String stringValue = value.toString();
                int floatIndex = stringValue.indexOf('.');
                return Integer.parseInt(
                        stringValue.indexOf('.') > -1 ?
                                stringValue.substring(0, floatIndex) :
                                stringValue,
                        base);
            } catch (NumberFormatException e) {
                throw new TypeMismatchException(this, value);
            }
        }
    }

    @Override
    public TObject clone(Object value) throws TypeMismatchException {
        return newInstance(value, getFromBase(), getToBase());
    }
    
    @Override
    public int size() {
        Integer value = getValue();
        return value != null ? value : 0;
    }

    @Override
    public String toString() {
        Integer value = getValue();
        return value != null ? Integer.toString(value, toBase) : "";
    }
    
    public static TInteger newInstance(Object value) throws TypeMismatchException {
        return newInstance(value, 10);
    }
    
    public static TInteger newInstance(Object value, int fromBase) throws TypeMismatchException {
        return newInstance(value, fromBase, 10);
    }
    
    public static TInteger newInstance(Object value, int fromBase, int toBase) throws TypeMismatchException {
        return new TInteger(value, fromBase, toBase);
    }
}
