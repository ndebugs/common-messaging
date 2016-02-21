/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.attribute;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class FilterMismatchException extends Exception {

    public FilterMismatchException(Filter attribute, Object value) {
        super("Filter (" + attribute + ") mismatch for value '" + value + "'.");
    }
}
