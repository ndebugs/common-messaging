/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TString;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LLowCase extends LObject<TString> {
    
    private LObject value;

    public LLowCase(LObject value) {
        this.value = value;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String resultValue = this.value.evaluate(bundle, field, value).toString();
        
        String result = resultValue.toLowerCase();
        return TString.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value};
    }
}
