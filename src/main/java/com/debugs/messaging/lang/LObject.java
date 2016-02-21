/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.evaluator.Evaluable;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class LObject<T extends TObject> implements Evaluable<T, Object> {
    
    public static final char ESCAPE = '\\';
    public static final char PREFIX_NAMESPACE = 'L';
    public static final char PREFIX = '[';
    public static final char SUFFIX = ']';
    public static final char FUNCTION_DELIMETER = ' ';
    public static final char PARAMETER_PREFIX = ':';
    public static final char PARAMETER_DELIMETER = ',';
    
    public static final char[] RESERVED_CHARS = {
        LObject.ESCAPE,
        LObject.PREFIX,
        LObject.SUFFIX,
        LObject.PARAMETER_PREFIX,
        LObject.PARAMETER_DELIMETER
    };
    
    public abstract T evaluate(MessageBundle bundle, Field field, Object value) throws Exception;
    
    public abstract Object[] params();
    
    public final String escape(String value) {
        for (char reservedChar : RESERVED_CHARS) {
            value = value.replace("" + reservedChar, ESCAPE + "" + reservedChar);
        }
        return value;
    }
    
    public final String name() {
        char[] ch = getClass().getSimpleName().toCharArray();
        ch[1] = Character.toLowerCase(ch[1]);
        return new String(ch, 1, ch.length - 1);
    }
    
    public final String fullName() {
        StringBuilder sb = new StringBuilder();
        
        String parentPackage = LObject.class.getPackage().getName();
        String selfPackage = getClass().getPackage().getName();
        if (selfPackage.length() > parentPackage.length()) {
            String simplifiedPackage = selfPackage.substring(parentPackage.length() + 1);
            sb.append(simplifiedPackage.replace('.', FUNCTION_DELIMETER))
                    .append(FUNCTION_DELIMETER);
        }
        
        sb.append(name());
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(PREFIX)
                .append(fullName());
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
                sb.append(param.toString());
            }
        }
        sb.append(SUFFIX);
        return sb.toString();
    }
    
    public String toVerbosedString() {
        String parentPackage = LObject.class.getPackage().getName();
        String selfPath = getClass().getName();
        String simplifiedPath = selfPath.substring(parentPackage.length() + 1);
        
        StringBuilder sb = new StringBuilder()
                .append(PREFIX)
                .append(simplifiedPath)
                .append('@')
                .append(hashCode());
        
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
                String result = param instanceof LObject ?
                        ((LObject) param).toVerbosedString() :
                        param.toString();
                sb.append(result);
            }
        }
        sb.append(SUFFIX);
        return sb.toString();
    }
}
