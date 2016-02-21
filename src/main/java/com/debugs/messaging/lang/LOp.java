/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class LOp<T extends TObject, V extends TObject> extends LObject<T> {

    private LObject<V> value1;
    private LObject<V> value2;

    public LOp(LObject<V> value1, LObject<V> value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public LObject<V> getValue1() {
        return value1;
    }

    public void setValue1(LObject<V> value1) {
        this.value1 = value1;
    }

    public LObject<V> getValue2() {
        return value2;
    }

    public void setValue2(LObject<V> value2) {
        this.value2 = value2;
    }

    @Override
    public Object[] params() {
        return new Object[] {value1, value2};
    }
}
