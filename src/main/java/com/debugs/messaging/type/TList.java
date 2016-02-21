/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TList extends TCollection<Object[], Integer> {

    public TList() {}

    private TList(Object[] value) throws TypeMismatchException {
        setValue(value);
    }

    @Override
    public Object getValueAt(Integer key) {
        Object[] value = getValue();
        return value != null ? value[key] : null;
    }

    @Override
    public Object[] parse(Object value) throws TypeMismatchException {
        if (value == null) {
            return null;
        } else if (value instanceof Object[]) {
            return (Object[]) value;
        } else {
            throw new TypeMismatchException(this, value);
        }
    }

    @Override
    public TObject clone(Object value) throws TypeMismatchException {
        return newInstance(value);
    }
    
    @Override
    public int size() {
        Object[] value = getValue();
        return value != null ? value.length : 0;
    }

    @Override
    protected Object[] params() {
        return null;
    }

    @Override
    public String toString() {
        Object[] value = getValue();
        if (value != null) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < value.length; i++) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(value[i]);
            }
            sb.append("]");
            return sb.toString();
        } else {
            return "";
        }
    }
    
    public static TList newInstance(Object value) throws TypeMismatchException {
        return new TList((Object[]) value);
    }
}
