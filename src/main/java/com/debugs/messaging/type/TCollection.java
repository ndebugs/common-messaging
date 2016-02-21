/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class TCollection<T, K> extends TObject<T> {

    public TCollection() {}

    public abstract Object getValueAt(K key);
    
    @Override
    protected Object[] params() {
        return null;
    }
}
