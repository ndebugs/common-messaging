/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.utils;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class Trimmer {
    
    private char[] characters;

    public Trimmer() {
        this(null);
    }

    public Trimmer(char[] characters) {
        this.characters = characters;
    }

    public char[] getCharacters() {
        return characters;
    }

    public void setCharacters(char[] characters) {
        this.characters = characters;
    }
    
    public boolean isTrimmedChar(char value) {
        if (characters != null) {
            for (char c : characters) {
                if (c == value) {
                    return true;
                }
            }
            return false;
        } else {
            return value <= ' ';
        }
    }
    
    public int findFirstIndex(String value) {
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            if (!isTrimmedChar(c)) {
                return i;
            }
        }
        return 0;
    }
    
    public int findLastIndex(String value) {
        int length = value.length();
        for (int i = length - 1; i >= 0; i--) {
            char c = value.charAt(i);
            if (!isTrimmedChar(c)) {
                return i + 1;
            }
        }
        return length;
    }
    
    public String trim(String value) {
        int length = value.length();
        int firstIndex = findFirstIndex(value);
        int lastIndex = firstIndex < length - 1 ? findLastIndex(value) : length;
        if (firstIndex == lastIndex) {
            return "";
        } else if (firstIndex == 0 && lastIndex == length) {
            return value;
        } else {
            return value.substring(firstIndex, lastIndex);
        }
    }
    
    public String trimLeft(String value) {
        int length = value.length();
        int firstIndex = findFirstIndex(value);
        if (firstIndex == length) {
            return "";
        } else if (firstIndex == 0) {
            return value;
        } else {
            return value.substring(firstIndex);
        }
    }
    
    public String trimRight(String value) {
        int length = value.length();
        int lastIndex = findLastIndex(value);
        if (lastIndex == 0) {
            return "";
        } else if (lastIndex == length) {
            return value;
        } else {
            return value.substring(0, lastIndex);
        }
    }
}
