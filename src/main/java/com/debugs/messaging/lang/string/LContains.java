/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TBoolean;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LContains extends LObject<TBoolean> {
    
    private LObject source;
    private LObject value;

    public LContains(LObject source, LObject value) {
        this.source = source;
        this.value = value;
    }

    public LObject getSource() {
        return source;
    }

    public void setSource(LObject source) {
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
        String resultSource = source.evaluate(bundle, field, value).toString();
        String resultValue = this.value.evaluate(bundle, field, value).toString();
        
        boolean result = resultSource != null ? resultSource.contains(resultValue) : false;
        return TBoolean.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {source, value};
    }
}
