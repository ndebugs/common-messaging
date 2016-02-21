/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TEntry;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LEntry extends LObject<TEntry> {
    
    private LObject key;
    private LObject value;

    public LEntry(LObject key, LObject value) {
        this.key = key;
        this.value = value;
    }

    public LObject getKey() {
        return key;
    }

    public void setKey(LObject key) {
        this.key = key;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    @Override
    public TEntry evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedKey = key.evaluate(bundle, field, value);
        TObject evaluatedValue = this.value.evaluate(bundle, field, value);
        
        return TEntry.newInstance(evaluatedKey.toString(), evaluatedValue);
    }

    @Override
    public Object[] params() {
        return new Object[] {key, value};
    }
}
