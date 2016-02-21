/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.message;

import com.debugs.messaging.Field;
import com.debugs.messaging.Message;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LGet extends LObject {
    
    private LObject key;

    public LGet(LObject key) {
        this.key = key;
    }

    public LObject getKey() {
        return key;
    }

    public void setKey(LObject key) {
        this.key = key;
    }

    @Override
    public TObject evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedKey = key.evaluate(bundle, field, value);
        
        Message message = bundle.getMessage();
        Object result = message.get(evaluatedKey.getValue());
        return TObject.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {key};
    }
}
