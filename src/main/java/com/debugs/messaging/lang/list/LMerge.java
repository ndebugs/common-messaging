/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.list;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.lang.LOp;
import com.debugs.messaging.type.TList;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LMerge extends LOp<TList, TList> {
    
    public LMerge(LObject<TList> value1, LObject<TList> value2) {
        super(value1, value2);
    }

    @Override
    public TList evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TList evaluatedValue1 = getValue1().evaluate(bundle, field, value);
        Object[] resultValue1 = evaluatedValue1.getValue();
        
        TList evaluatedValue2 = getValue2().evaluate(bundle, field, value);
        Object[] resultValue2 = evaluatedValue2.getValue();
        
        int len1 = resultValue1.length;
        int len2 = resultValue2.length;
        
        Object[] result = new Object[len1 + len2];
        System.arraycopy(resultValue1, 0, result, 0, len1);
        System.arraycopy(resultValue2, 0, result, len1, len2);
        
        return TList.newInstance(result);
    }
}
