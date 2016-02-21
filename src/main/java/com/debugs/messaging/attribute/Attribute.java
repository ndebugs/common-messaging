/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.attribute;

import com.debugs.messaging.evaluator.Evaluable;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class Attribute implements Evaluable<Void, TObject> {
    
    public final String name() {
        return getClass().getSimpleName();
    }
}
