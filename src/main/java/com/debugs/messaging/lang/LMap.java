/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TEntry;
import com.debugs.messaging.type.TMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LMap extends LVarArgs<TMap, LEntry> {
    
    public LMap(LEntry... values) throws LangArgumentsOutOfRangeException {
        super(values);
    }

    @Override
    protected void init() {}

    @Override
    public TMap evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        LEntry[] values = getValues();
        Map result = new HashMap(values.length);
        for (LEntry entry : values) {
            TEntry evaluatedEntry = entry.evaluate(bundle, field, value);
            result.put(evaluatedEntry.getKey(), evaluatedEntry.getValue());
        }
        return TMap.newInstance(result);
    }
}
