/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.message;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LKey extends LObject {
    
    @Override
    public TObject evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        return TObject.newInstance(field.getId());
    }

    @Override
    public Object[] params() {
        return null;
    }
}
