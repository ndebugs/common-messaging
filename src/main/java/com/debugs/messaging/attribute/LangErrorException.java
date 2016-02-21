/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.attribute;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LangErrorException extends Exception {

    public LangErrorException(Attribute attribute, Object value, Throwable cause) {
        super("Lang error for attribute '" + attribute.name() + "' with value '" + value + "'.", cause);
    }
}
