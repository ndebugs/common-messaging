/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.parser;

import com.debugs.messaging.builder.LangBuilder;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.lang.LString;
import com.debugs.messaging.lang.string.LConcat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LangParser {

    private static final int STATE_DONE = -1;
    private static final int STATE_NONE = 0;
    private static final int STATE_FUNCTION = 1;
    private static final int STATE_ARGUMENTS = 2;
    
    private String data;
    private int offset;
    private int state;
    private LangParser parent;

    public LangParser(String data) {
        this(data, 0, null);
    }

    private LangParser(String data, int offset, LangParser parent) {
        this.data = data;
        this.offset = offset;
        this.parent = parent;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    protected void updateState(int state) throws ParserException {
        switch (state) {
            case STATE_NONE:
                if (this.state == STATE_NONE) {
                    throw new ParserException(offset, data.charAt(offset), LObject.PREFIX);
                }
                break;
            case STATE_ARGUMENTS:
                if (this.state != STATE_FUNCTION) {
                    throw new ParserException(offset, data.charAt(offset), LObject.PREFIX);
                }
                break;
            case STATE_FUNCTION:
            case STATE_DONE:
                if (this.state != STATE_NONE) {
                    char c = offset < data.length() ? data.charAt(offset) : 0;
                    throw new ParserException(offset, c, LObject.SUFFIX);
                }
                break;
        }
        this.state = state;
    }
    
    public boolean isEscapedChar(char c) {
        for (char reservedChar : LObject.RESERVED_CHARS) {
            if (reservedChar == c) {
                return true;
            }
        }
        return false;
    }
    
    private LObject parseLang(String name) throws Exception {
        offset++;
        LangBuilder builder = new LangBuilder(name);
        while (state == STATE_ARGUMENTS) {
            LangParser parser = new LangParser(data, offset, this);
            LObject value = parser.parse();
            builder.addArgument(value);
            offset = parser.getOffset();
        }
        return builder.build();
    }
    
    public LObject parseNext() throws Exception {
        boolean isEscaped = false;
        StringBuilder tempString = new StringBuilder();
        int length = data.length();
        for (; offset < length; offset++) {
            char c = data.charAt(offset);
            if (!isEscaped) {
                if (c == LObject.ESCAPE) {
                    isEscaped = true;
                    continue;
                } else if (c == LObject.PREFIX) {
                    updateState(STATE_FUNCTION);
                    break;
                } else if (c == LObject.SUFFIX) {
                    if (parent != null && state == STATE_NONE) {
                        updateState(STATE_DONE);
                        parent.updateState(STATE_NONE);
                        break;
                    } else {
                        updateState(STATE_NONE);
                        return parseLang(tempString.toString());
                    }
                } else if (c == LObject.PARAMETER_PREFIX) {
                    updateState(STATE_ARGUMENTS);
                    return parseLang(tempString.toString());
                } else if (c == LObject.PARAMETER_DELIMETER) {
                    if (parent != null) {
                        updateState(STATE_DONE);
                        break;
                    } else {
                        throw new ParserException(offset, c, LObject.PARAMETER_PREFIX);
                    }
                }
            } else if (isEscapedChar(c)) {
                isEscaped = false;
            } else {
                throw new NonEscapedCharacterException(offset, c);
            }
            tempString.append(c);
        }
        
        if (offset == length) {
            updateState(STATE_DONE);
            if (parent != null) {
                parent.setOffset(offset);
                parent.updateState(STATE_DONE);
            }
        } else {
            offset++;
        }
        
        return tempString.length() > 0 ? LString.newInstance(tempString.toString()): null;
    }

    public LObject parse() throws Exception {
        List<LObject> list = new ArrayList();
        while (state != STATE_DONE) {
            LObject lang = parseNext();
            if (lang != null) {
                list.add(lang);
            }
        }
        if (list.size() > 1) {
            LObject[] objs = new LObject[list.size()];
            list.toArray(objs);
            return LConcat.newInstance(objs);
        } else {
            return list.isEmpty() ? LString.newInstance("") : list.get(0);
        }
    }
}
