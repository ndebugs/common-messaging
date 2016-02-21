/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.evaluator;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public interface Evaluable<T, V> {
    
    public abstract T evaluate(MessageBundle bundle, Field field, V value) throws Exception;
}
