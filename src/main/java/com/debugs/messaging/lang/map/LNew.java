/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.map;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TInteger;
import com.debugs.messaging.type.TMap;
import com.debugs.messaging.type.TNumber;
import com.debugs.messaging.type.TObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LNew extends LObject<TMap> {
    
    private LObject capacity;

    public LNew(LObject capacity) {
        this.capacity = capacity;
    }

    public LObject getCapacity() {
        return capacity;
    }

    public void setCapacity(LObject capacity) {
        this.capacity = capacity;
    }
    
    @Override
    public TMap evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TObject evaluatedCapacity = capacity.evaluate(bundle, field, value);
        TNumber parsedCapacity = evaluatedCapacity instanceof TNumber ?
                (TNumber) evaluatedCapacity :
                TInteger.newInstance(evaluatedCapacity.getValue());
        
        Map result = new HashMap(parsedCapacity.integerValue());
        return TMap.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {capacity};
    }
}
