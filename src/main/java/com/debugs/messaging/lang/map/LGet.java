/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.map;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TMap;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LGet extends LObject {
    
    private LObject<TMap> source;
    private LObject key;
    
    public LGet(LObject<TMap> source, LObject key) {
        this.source = source;
        this.key = key;
    }

    public LObject<TMap> getSource() {
        return source;
    }

    public void setSource(LObject<TMap> source) {
        this.source = source;
    }

    public LObject getKey() {
        return key;
    }

    public void setKey(LObject key) {
        this.key = key;
    }

    @Override
    public TObject evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TMap evaluatedSource = source.evaluate(bundle, field, value);
        
        String resultKey = key.evaluate(bundle, field, value).toString();
        
        Object result = evaluatedSource.getValueAt(resultKey);
        return TObject.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {source, key};
    }
}
