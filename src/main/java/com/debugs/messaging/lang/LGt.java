/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.type.TBoolean;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LGt extends LOpNum<TBoolean> {
    
    public LGt(LObject value1, LObject value2) {
        super(value1, value2);
    }

    @Override
    protected TBoolean execute(int value1, int value2) throws Exception {
        return TBoolean.newInstance(value1 > value2);
    }

    @Override
    protected TBoolean execute(double value1, double value2) throws Exception {
        return TBoolean.newInstance(value1 > value2);
    }
}
