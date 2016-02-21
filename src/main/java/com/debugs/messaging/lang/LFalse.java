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
public class LFalse extends LObject<TBoolean> {
    
    @Override
    public TBoolean evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        return TBoolean.newInstance(false);
    }

    @Override
    public Object[] params() {
        return null;
    }
}
