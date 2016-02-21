/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.utils;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class Padder {
    
    private final char character;
    private final boolean rtl;

    public Padder(char character, boolean rtl) {
        this.character = character;
        this.rtl = rtl;
    }

    public char getCharacter() {
        return character;
    }
    public boolean isRTL() {
        return rtl;
    }
    
    public String pad(String source, int length) {
        int sourceLength = source.length();
        int resultLength = Math.max(sourceLength, length);
        char[] ch = new char[resultLength];
        
        int offset = 0;
        int padStart;
        int padEnd;
        if (rtl) {
            padStart = 0;
            padEnd = offset = resultLength - sourceLength;
        } else {
            padStart = sourceLength;
            padEnd = resultLength;
        }
        source.getChars(0, sourceLength, ch, offset);
        
        while (padStart < padEnd) {
            ch[padStart++] = character;
        }
        return new String(ch);
    }
    
    public String unpad(String source) {
        int index = 0;
        int endIndex;
        int increment;
        if (rtl) {
            endIndex = source.length();
            increment = 1;
        } else {
            index = source.length() - 1;
            endIndex = -1;
            increment = -1;
        }
        while (index != endIndex) {
            char c = source.charAt(index);
            if (c != character) {
                return !rtl ?
                        source.substring(0, index + 1) :
                        source.substring(index);
            }
            index += increment;
        }
        return source;
    }
}
