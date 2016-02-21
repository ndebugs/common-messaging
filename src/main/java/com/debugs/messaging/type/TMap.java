/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TMap extends TCollection<Map<Object, Object>, Object> {

    public TMap() {}

    private TMap(Map value) throws TypeMismatchException {
        setValue(value);
    }

    @Override
    public Object getValueAt(Object key) {
        Map value = getValue();
        return value != null ? value.get(key) : null;
    }

    @Override
    public Map<Object, Object> parse(Object value) throws TypeMismatchException {
        if (value == null) {
            return null;
        } else if (value instanceof Map) {
            return (Map<Object, Object>) value;
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
        Map value = getValue();
        return value != null ? value.size() : 0;
    }

    @Override
    protected Object[] params() {
        return null;
    }

    @Override
    public String toString() {
        Map<Object, Object> value = getValue();
        if (value != null) {
            StringBuilder sb = new StringBuilder("{");
            int i = 0;
            for (Entry e : value.entrySet()) {
                if (i++ > 0) {
                    sb.append(',');
                }
                sb.append(e.getKey())
                        .append(':')
                        .append(e.getValue());
            }
            sb.append("}");
            return sb.toString();
        } else {
            return "";
        }
    }
    
    public static TMap newInstance(Object value) throws TypeMismatchException {
        return new TMap((Map) value);
    }
}
