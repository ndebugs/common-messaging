/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.map;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TBoolean;
import com.debugs.messaging.type.TMap;
import com.debugs.messaging.type.TObject;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LContains extends LObject<TBoolean> {
    
    private LObject<TMap> source;
    private LObject value;

    public LContains(LObject<TMap> source, LObject value) {
        this.source = source;
        this.value = value;
    }

    public LObject<TMap> getSource() {
        return source;
    }

    public void setSource(LObject<TMap> source) {
        this.source = source;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TMap evaluatedSource = source.evaluate(bundle, field, value);
        Map<Object, Object> resultSource = evaluatedSource.getValue();
        
        TObject evaluatedValue = this.value.evaluate(bundle, field, value);
        
        for (Entry e : resultSource.entrySet()) {
            if (evaluatedValue.equals(e.getValue())) {
                return TBoolean.newInstance(true);
            }
        }
        return TBoolean.newInstance(false);
    }

    @Override
    public Object[] params() {
        return new Object[] {source, value};
    }
}
