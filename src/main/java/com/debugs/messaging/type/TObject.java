/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

import java.util.Map;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class TObject<T> {
    
    public static final char[] RESERVED_CHARS = {
        TObject.ESCAPE,
        TObject.PARAMETER_PREFIX,
        TObject.PARAMETER_SUFFIX,
        TObject.PARAMETER_DELIMETER
    };
    
    public static final char ESCAPE = '\\';
    public static final char PREFIX_NAMESPACE = 'T';
    public static final char PARAMETER_PREFIX = '(';
    public static final char PARAMETER_SUFFIX = ')';
    public static final char PARAMETER_DELIMETER = ',';
    
    private T value;

    public TObject() {}

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
    public abstract T parse(Object value) throws TypeMismatchException;
    
    public abstract TObject clone(Object value) throws TypeMismatchException;
    
    public abstract int size();

    protected abstract Object[] params();
    
    public final String escape(String value) {
        for (char reservedChar : RESERVED_CHARS) {
            value = value.replace("" + reservedChar, ESCAPE + "" + reservedChar);
        }
        return value;
    }

    public boolean isValueEquals(TObject obj) {
        Object objValue = obj.getValue();
        if (value == null || objValue == null) {
            return value == objValue;
        } else {
            String v1 = toString();
            String v2 = obj.toString();
            return v1.equals(v2);
        }
    }
    
    public final String name() {
        char[] ch = getClass().getSimpleName().toCharArray();
        return new String(ch, 1, ch.length - 1);
    }
    
    public final String alias() {
        StringBuilder sb = new StringBuilder(name());
        Object[] params = params();
        if (params != null) {
            sb.append(PARAMETER_PREFIX);
            boolean first = true;
            for (Object param : params) {
                if (!first) {
                    sb.append(PARAMETER_DELIMETER);
                } else {
                    first = false;
                }
                String result = escape(param.toString());
                sb.append(result);
            }
            sb.append(PARAMETER_SUFFIX);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Object objSource = obj instanceof TObject ? ((TObject) obj).getValue() : obj;
        String objValue = objSource != null ? obj.toString() : null;
        if (value == null || objValue == null) {
            return value == objValue;
        } else {
            return toString().equals(objValue);
        }
    }
    
    public static TObject newInstance(Object value) throws TypeMismatchException {
        if (value == null) {
            return TNull.newInstance();
        } else if (value instanceof Boolean) {
            return TBoolean.newInstance(value);
        } else if (value instanceof Integer) {
            return TInteger.newInstance(value);
        } else if (value instanceof Double) {
            return TDecimal.newInstance(value);
        } else if (value instanceof Object[]) {
            return TList.newInstance(value);
        } else if (value instanceof Map) {
            return TMap.newInstance(value);
        } else {
            return TString.newInstance(value);
        }
    }
}
