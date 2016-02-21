/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TString;
import com.debugs.messaging.utils.Trimmer;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LTrim extends LObject<TString> {
    
    private LObject value;
    private LObject characters;

    public LTrim(LObject value) {
        this(value, null);
    }

    public LTrim(LObject value, LObject characters) {
        this.value = value;
        this.characters = characters;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }
    
    public LObject getCharacters() {
        return characters;
    }

    public void setCharacters(LObject characters) {
        this.characters = characters;
    }

    protected String execute(Trimmer trimmer, String value) {
        return trimmer.trim(value);
    }
    
    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String valueResult = this.value.evaluate(bundle, field, value).toString();
        
        Trimmer trimmer;
        if (characters != null) {
            String charsResult = characters.evaluate(bundle, field, value).toString();
            char[] chars = charsResult.toCharArray();
            trimmer = new Trimmer(chars);
        } else {
            trimmer = new Trimmer();
        }
        
        String result = execute(trimmer, valueResult);
        return TString.newInstance(result);
    }

    @Override
    public Object[] params() {
        return characters == null ?
                new Object[] {value} :
                new Object[] {value, characters};
    }
}
