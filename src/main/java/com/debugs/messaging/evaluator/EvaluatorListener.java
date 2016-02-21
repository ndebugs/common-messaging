/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.evaluator;

import com.debugs.messaging.Field;
import java.util.Map;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public interface EvaluatorListener<F extends Field, V> {
    
    public void onEvaluateObject(FieldEvaluator evaluator, F field, V value, int depth) throws Exception;
    
    public void onPreEvaluateArray(FieldEvaluator evaluator, F field, Object[] value, int depth) throws Exception;
    
    public void onPostEvaluateArray(FieldEvaluator evaluator, F field, Object[] value, int depth) throws Exception;
    
    public void onPreEvaluateMap(FieldEvaluator evaluator, F field, Map value, int depth) throws Exception;
    
    public void onPostEvaluateMap(FieldEvaluator evaluator, F field, Map value, int depth) throws Exception;
}
