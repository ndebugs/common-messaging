/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.evaluator;

import com.debugs.messaging.Field;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public interface EvaluatorHandler<F extends Field, K, V> extends EvaluatorListener<F, V> {
    
    public K nextKey(MessageEvaluator evaluator);
    
    public V nextValue(MessageEvaluator evaluator, F field) throws FieldEvaluationException;
}
