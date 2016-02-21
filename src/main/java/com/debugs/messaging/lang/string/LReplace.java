/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TString;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LReplace extends LObject<TString> {
    
    private LObject source;
    private LObject pattern;
    private LObject value;

    public LReplace(LObject source, LObject pattern, LObject value) {
        this.source = source;
        this.pattern = pattern;
        this.value = value;
    }

    public LObject getSource() {
        return source;
    }

    public void setSource(LObject source) {
        this.source = source;
    }

    public LObject getPattern() {
        return pattern;
    }

    public void setPattern(LObject pattern) {
        this.pattern = pattern;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String resultSource = source.evaluate(bundle, field, value).toString();
        String resultPattern = pattern.evaluate(bundle, field, value).toString();
        String resultValue = this.value.evaluate(bundle, field, value).toString();
        
        String result = resultSource.replaceAll(resultPattern, resultValue);
        return TString.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {source, pattern, value};
    }
}
