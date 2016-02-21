/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TString extends TObject<String> {

    public TString() {}
    
    private TString(Object value) throws TypeMismatchException {
        String parsedValue = parse(value);
        setValue(parsedValue);
    }

    @Override
    public String parse(Object value) throws TypeMismatchException {
        return value != null ? value.toString() : null;
    }

    @Override
    public TObject clone(Object value) throws TypeMismatchException {
        return newInstance(value);
    }
    
    @Override
    public int size() {
        String value = getValue();
        return value != null ? value.length() : 0;
    }
    
    @Override
    protected Object[] params() {
        return null;
    }

    @Override
    public String toString() {
        String value = getValue();
        return value != null ? value : "";
    }
    
    public static TString newInstance(Object value) throws TypeMismatchException {
        return new TString(value);
    }
}
