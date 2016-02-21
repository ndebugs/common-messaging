/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.map;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TList;
import com.debugs.messaging.type.TMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LKeys extends LObject<TList> {
    
    private LObject<TMap> value;

    public LKeys(LObject<TMap> value) {
        this.value = value;
    }

    public LObject<TMap> getValue() {
        return value;
    }

    public void setValue(LObject<TMap> value) {
        this.value = value;
    }

    @Override
    public TList evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TMap evaluatedValue = this.value.evaluate(bundle, field, value);
        Map resultValue = evaluatedValue.getValue();
        
        Set keys = resultValue.keySet();
        Object[] result = new Object[resultValue.size()];
        keys.toArray(result);
        return TList.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
