/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class MessageBundle {
    
    private final Message temp;
    private final Message message;

    public MessageBundle(Message message) {
        this(null, message);
    }

    public MessageBundle(Message temp, Message message) {
        this.temp = temp;
        this.message = message;
    }

    public Message getTemp() {
        return temp;
    }

    public Message getMessage() {
        return message;
    }
}
