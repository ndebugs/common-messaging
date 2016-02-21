/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class LVarArgs<T extends TObject, V extends LObject> extends LObject<T> {

    private V[] values;
    private int minArgs;

    public LVarArgs(V... values) throws LangArgumentsOutOfRangeException {
        init();
        
        int len = values != null ? values.length : 0;
        if (len < minArgs) {
            throw new LangArgumentsOutOfRangeException(this, len, minArgs);
        }
        this.values = values;
    }

    protected abstract void init();
    
    public int getMinArgs() {
        return minArgs;
    }

    protected void setMinArgs(int minArgs) {
        this.minArgs = minArgs;
    }

    public V[] getValues() {
        return values;
    }

    public void setValues(V... values) {
        this.values = values;
    }

    @Override
    public Object[] params() {
        return values;
    }
}
