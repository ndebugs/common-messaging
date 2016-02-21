/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.attribute;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LengthValidationException extends Exception {

    public LengthValidationException(Length attribute, Object value) {
        super("Invalid length (" + attribute + ") for value '" + value + "'.");
    }
}
