/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.type;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TypeMismatchException extends Exception {

    public TypeMismatchException(TObject type, Object value) {
        super("Type " + type.name() + " did not match with value '" + value + "'.");
    }
}
