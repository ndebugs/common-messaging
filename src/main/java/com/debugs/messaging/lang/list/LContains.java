/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.list;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TBoolean;
import com.debugs.messaging.type.TList;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LContains extends LObject<TBoolean> {
    
    private LObject<TList> source;
    private LObject value;

    public LContains(LObject<TList> source, LObject value) {
        this.source = source;
        this.value = value;
    }

    public LObject<TList> getSource() {
        return source;
    }

    public void setSource(LObject<TList> source) {
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
        TList evaluatedSource = source.evaluate(bundle, field, value);
        TObject evaluatedValue = this.value.evaluate(bundle, field, value);
        
        for (Object o : evaluatedSource.getValue()) {
            if (evaluatedValue.equals(o)) {
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
