/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TList;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LList extends LVarArgs<TList, LObject> {
    
    public LList(LObject... values) throws LangArgumentsOutOfRangeException {
        super(values);
    }

    @Override
    protected void init() {}

    @Override
    public TList evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        LObject[] values = getValues();
        Object[] result = new Object[values.length];
        for (int i = 0; i < values.length; i++) {
            TObject evaluatedValue = values[i].evaluate(bundle, field, value);
            result[i] = evaluatedValue.getValue();
        }
        return TList.newInstance(result);
    }
}
