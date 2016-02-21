/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.evaluator;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.attribute.Attribute;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class FieldEvaluator {

    private final MessageBundle bundle;
    private final EvaluatorListener listener;

    public FieldEvaluator(MessageBundle bundle, EvaluatorListener listener) {
        this.bundle = bundle;
        this.listener = listener;
    }

    public MessageBundle getBundle() {
        return bundle;
    }

    public EvaluatorListener getListener() {
        return listener;
    }
  
    public Object evaluateObject(Field field, Object value) throws Exception {
        try {
            return evaluateObject(field, value, 0);
        } catch (Exception e) {
            throw new FieldEvaluationException(field, e);
        }
    }
    
    private Object evaluateObject(Field field, Object value, int depth) throws Exception {
        TObject fieldType = field.getType();
        LObject fieldValue = field.getValue();
        
        TObject clonedType;
        if (fieldValue != null) {
            clonedType = fieldValue.evaluate(bundle, field, value);
            if (clonedType.getClass() != fieldType.getClass()) {
                value = clonedType.getValue();
                clonedType = fieldType.clone(value);
            }
        } else {
            clonedType = fieldType.clone(value);
        }
        
        List<Attribute> attributes = field.attributes();
        for (int i = attributes.size() - 1; i >= 0; i--) {
            Attribute attribute = attributes.get(i);
            if (attribute != null) {
                attribute.evaluate(bundle, field, clonedType);
            }
        }
        
        value = clonedType.getValue();
        if (value instanceof Object[]) {
            evaluateArray(field, (Object[]) value, depth);
        } else if (value instanceof Map) {
            evaluateMap(field, (Map) value, depth);
        } else {
            listener.onEvaluateObject(this, field, value, depth);
        }
        return value;
    }
    
    private void evaluateArray(Field field, Object[] value, int depth) throws Exception {
        listener.onPreEvaluateArray(this, field, value, depth);
        
        Map<Object, Field> childs = field.getChilds();
        Field defaultChild = childs != null ? childs.get(null) : null;
        for (int i = 0; i < value.length; i++) {
            Field child = childs != null ? childs.get(i) : null;
            if (child == null) {
                if (defaultChild != null) {
                    child = defaultChild;
                } else {
                    throw new NoFieldException(i);
                }
            }
            value[i] = evaluateObject(child, value[i], depth + 1);
        }
        
        listener.onPostEvaluateArray(this, field, value, depth);
    }
    
    private void evaluateMap(Field field, Map<String, Object> value, int depth) throws Exception {
        listener.onPreEvaluateMap(this, field, value, depth);
        
        List<String> keys = new ArrayList(value.keySet());
        Map<Object, Field> childs = field.getChilds();
        if (childs != null) {
            for (Field f : childs.values()) {
                String key = (String) f.getId();
                value.put(key, evaluateObject(f, value.get(key), depth + 1));
                keys.remove(key);
            }
        }
        
        if (!keys.isEmpty()) {
            throw new NoFieldException(keys.get(0));
        }
        listener.onPostEvaluateMap(this, field, value, depth);
    }
}
