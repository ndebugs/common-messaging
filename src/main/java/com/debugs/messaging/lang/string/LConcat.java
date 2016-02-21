/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.lang.LangArgumentsOutOfRangeException;
import com.debugs.messaging.lang.LVarArgs;
import com.debugs.messaging.type.TObject;
import com.debugs.messaging.type.TString;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LConcat extends LVarArgs<TString, LObject> {
    
    private LConcat(LObject... values) throws LangArgumentsOutOfRangeException {
        super(values);
    }

    @Override
    protected void init() {
        setMinArgs(2);
    }

    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        StringBuilder result = new StringBuilder();
        for (LObject v : getValues()) {
            TObject evaluatedV = v.evaluate(bundle, field, value);
            if (evaluatedV.getValue() != null) {
                result.append(evaluatedV.toString());
            }
        }
        return TString.newInstance(result.toString());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LObject value : getValues()) {
            sb.append(value);
        }
        return sb.toString();
    }
    
    public static LConcat newInstance(LObject... values) throws LangArgumentsOutOfRangeException {
        return new LConcat(values);
    }
}
