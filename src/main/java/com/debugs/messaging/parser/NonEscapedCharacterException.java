/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.parser;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class NonEscapedCharacterException extends Exception {

    public NonEscapedCharacterException(int index, char value) {
        super("'" + value + "' is not an escaped character, at index " + index + ".");
    }
    
}
