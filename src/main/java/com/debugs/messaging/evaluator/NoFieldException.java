/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.evaluator;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class NoFieldException extends Exception {

    public NoFieldException(Object key) {
        super("No field found for key '" + key + "'.");
    }
}
