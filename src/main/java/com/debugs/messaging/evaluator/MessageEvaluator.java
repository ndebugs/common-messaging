/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.evaluator;

import com.debugs.messaging.Field;
import com.debugs.messaging.Message;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.Packager;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class MessageEvaluator {
    
    private Packager packager;
    private EvaluatorHandler handler;

    public MessageEvaluator(Packager packager, EvaluatorHandler handler) {
        this.packager = packager;
        this.handler = handler;
    }

    public Packager getPackager() {
        return packager;
    }

    public void setPackager(Packager packager) {
        this.packager = packager;
    }

    public EvaluatorHandler getHandler() {
        return handler;
    }

    public void setHandler(EvaluatorHandler handler) {
        this.handler = handler;
    }

    public void evaluate(MessageBundle bundle) throws Exception {
        FieldEvaluator evaluator = new FieldEvaluator(bundle, handler);
        
        Object key;
        Message message = bundle.getMessage();
        while ((key = handler.nextKey(this)) != null) {
            Field field = packager.get(key);
            if (field == null) {
                throw new NoFieldException(key);
            }
            
            Object value = handler.nextValue(this, field);
            value = evaluator.evaluateObject(field, value);
            if (value != null) {
                message.set(key, value);
            }
        }
    }
}
