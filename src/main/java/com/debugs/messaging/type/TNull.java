/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TNull extends TObject {

    private TNull() {}
    
    @Override
    public Object parse(Object value) throws TypeMismatchException {
        return null;
    }

    @Override
    public TObject clone(Object value) throws TypeMismatchException {
        return newInstance();
    }
    
    @Override
    public int size() {
        return 0;
    }
    
    @Override
    protected Object[] params() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
    
    public static TNull newInstance() throws TypeMismatchException {
        return new TNull();
    }
}
