/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.builder;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class NoMatchingArgumentsException extends Exception {

    public NoMatchingArgumentsException(Class target) {
        super("No matching arguments for class " + target.getName() + ".");
    }
}
