/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TBoolean;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LOr extends LVarArgs<TBoolean, LObject<TBoolean>> {
    
    public LOr(LObject<TBoolean>... values) throws LangArgumentsOutOfRangeException {
        super(values);
    }

    @Override
    protected void init() {
        setMinArgs(2);
    }

    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        for (LObject<TBoolean> v : getValues()) {
            TBoolean evaluatedValue = v.evaluate(bundle, field, value);
            if (evaluatedValue.getValue()) {
                return TBoolean.newInstance(true);
            }
        }
        return TBoolean.newInstance(false);
    }
}
