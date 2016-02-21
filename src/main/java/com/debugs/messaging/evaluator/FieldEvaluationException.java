/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.evaluator;

import com.debugs.messaging.Field;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class FieldEvaluationException extends Exception {

    public FieldEvaluationException(Field field, Throwable cause) {
        super("Evaluation error on field '" + field.getId() + "'.", cause);
    }
}
