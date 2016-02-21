/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TEntry extends TObject<Object> {

    private final String key;
    
    private TEntry(String key, Object value) throws TypeMismatchException {
        this.key = key;
        setValue(value);
    }

    public String getKey() {
        return key;
    }

    @Override
    public Object parse(Object value) throws TypeMismatchException {
        return null;
    }

    @Override
    public TObject clone(Object value) throws TypeMismatchException {
        return null;
    }
    
    @Override
    public int size() {
        return getValue() != null ? 1 : 0;
    }

    @Override
    protected Object[] params() {
        return null;
    }

    @Override
    public String toString() {
        Object value = getValue();
        if (key != null || value != null) {
            return key + ":" + value;
        } else {
            return "";
        }
    }
    
    public static TEntry newInstance(String key, Object value) throws TypeMismatchException {
        return new TEntry(key, value);
    }
}
