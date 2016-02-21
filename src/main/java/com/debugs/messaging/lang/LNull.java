/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TNull;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LNull extends LObject {
    
    @Override
    public TObject evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        return TNull.newInstance();
    }

    @Override
    public Object[] params() {
        return null;
    }
}
