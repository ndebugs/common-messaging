/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging;

import java.util.List;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public interface Message<K, V> {
    
    public V get(K key);

    public void set(K key, V value);
    
    public List<K> keys();
}
