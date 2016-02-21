/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.attribute;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class RequiredException extends Exception {

    public RequiredException(Required attribute) {
        super("Field is required (" + attribute + "), cannot be " + (attribute.isEmptyAllowed() ? "NULL" : "empty") + ".");
    }
}
