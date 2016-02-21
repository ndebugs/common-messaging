/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TBoolean extends TObject<Boolean> {

    public TBoolean() {}

    private TBoolean(Boolean value) throws TypeMismatchException {
        setValue(value);
    }
    
    @Override
    public Boolean parse(Object value) throws TypeMismatchException {
        if (value == null) {
            return null;
        } else if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            throw new TypeMismatchException(this, value);
        }
    }

    @Override
    public TObject clone(Object value) throws TypeMismatchException {
        Boolean parsedValue = parse(value);
        return newInstance(parsedValue);
    }

    @Override
    public int size() {
        Boolean value = getValue();
        return value != null && value ? 1 : 0;
    }
    
    @Override
    protected Object[] params() {
        return null;
    }

    @Override
    public String toString() {
        Boolean value = getValue();
        return value != null ? value.toString() : "";
    }
    
    public static TBoolean newInstance(Object value) throws TypeMismatchException {
        return new TBoolean((Boolean) value);
    }
}
