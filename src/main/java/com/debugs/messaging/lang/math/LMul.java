/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.math;

import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.lang.LOpNum;
import com.debugs.messaging.type.TDecimal;
import com.debugs.messaging.type.TInteger;
import com.debugs.messaging.type.TNumber;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LMul extends LOpNum<TNumber> {
    
    public LMul(LObject value1, LObject value2) {
        super(value1, value2);
    }

    @Override
    protected TNumber execute(int value1, int value2) throws Exception {
        return TInteger.newInstance(value1 * value2);
    }

    @Override
    protected TNumber execute(double value1, double value2) throws Exception {
        return TDecimal.newInstance(value1 * value2);
    }
}
