/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TCharacter extends TObject<Character> {

    public TCharacter() {}

    private TCharacter(Object value) throws TypeMismatchException {
        Character parsedValue = parse(value);
        setValue(parsedValue);
    }
    
    @Override
    public Character parse(Object value) throws TypeMismatchException {
        if (value == null) {
            return null;
        } else if (value instanceof Character) {
            return (Character) value;
        } else {
            String valueString = value.toString();
            if (valueString.length() == 0) {
                return 0;
            } else if (valueString.length() == 1) {
                return valueString.charAt(0);
            } else {
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
        Character value = getValue();
        return value != null && value > 0 ? 1 : 0;
    }
    
    @Override
    protected Object[] params() {
        return null;
    }

    @Override
    public String toString() {
        Character value = getValue();
        return value != null && value > 0 ? value.toString() : "";
    }
    
    public static TCharacter newInstance(Object value) throws TypeMismatchException {
        return new TCharacter(value);
    }
}
