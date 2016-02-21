/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LangArgumentsOutOfRangeException extends Exception {

    public LangArgumentsOutOfRangeException(LObject target, int size, int min) {
        super("Arguments out of range for lang '" + target.fullName() + "' with size: " + size + ", expected minimum: " + min + ".");
    }
}
