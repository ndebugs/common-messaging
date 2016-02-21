/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.parser;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class ParserException extends Exception {

    public ParserException(int index, char value, char expected) {
        super("Invalid character '" + (value > 0 ? value : "") + "' at index " + index + ", expected '" + expected + "'.");
    }
}
