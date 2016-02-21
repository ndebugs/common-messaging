/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.parser;

import com.debugs.messaging.builder.TypeBuilder;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TypeParser {
    
    private static final int STATE_NONE = 0;
    private static final int STATE_ARGUMENTS = 1;
    
    private String data;
    private int offset;
    private int state;

    public TypeParser(String data) {
        this.data = data;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public boolean isEscapedChar(char c) {
        for (char reservedChar : TObject.RESERVED_CHARS) {
            if (reservedChar == c) {
                return true;
            }
        }
        return false;
    }

    private String next() throws Exception {
        StringBuilder tempString = new StringBuilder();
        boolean isEscaped = false;
        for (; offset < data.length(); offset++) {
            char c = data.charAt(offset);
            
            if (!isEscaped && c == TObject.ESCAPE) {
                isEscaped = true;
                continue;
            } else if (isEscaped) {
                if (!isEscapedChar(c)) {
                    throw new NonEscapedCharacterException(offset, c);
                }
                isEscaped = false;
            } else if (c == TObject.PARAMETER_PREFIX) {
                if (state == STATE_NONE) {
                    state = STATE_ARGUMENTS;
                    break;
                } else {
                    throw new ParserException(offset, c, TObject.PARAMETER_SUFFIX);
                }
            } else if (c == TObject.PARAMETER_SUFFIX) {
                if (state == STATE_ARGUMENTS) {
                    state = STATE_NONE;
                    break;
                } else {
                    throw new ParserException(offset, c, TObject.PARAMETER_PREFIX);
                }
            } else if (c == TObject.PARAMETER_DELIMETER) {
                if (state == STATE_ARGUMENTS) {
                    break;
                } else {
                    throw new ParserException(offset, c, TObject.PARAMETER_PREFIX);
                }
            }
            tempString.append(c);
        }
        if (offset < data.length()) {
            offset++;
        } else if (state != STATE_NONE) {
            throw new ParserException(offset, (char) 0, TObject.PARAMETER_SUFFIX);
        }
        return tempString.toString();
    }
    
    public TObject parse() throws Exception {
        String name = next();
        TypeBuilder builder = new TypeBuilder(name);
        while (state != STATE_NONE) {
            String arg = next();
            if (arg != null) {
                builder.addArgument(arg);
            }
        }
        return builder.build();
    }
}
