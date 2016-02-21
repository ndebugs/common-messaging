/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.list;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TList;
import com.debugs.messaging.type.TString;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LJoin extends LObject<TString> {
    
    private LObject<TList> value;
    private LObject delimeter;

    public LJoin(LObject<TList> value) {
        this(value, null);
    }

    public LJoin(LObject<TList> value, LObject delimeter) {
        this.value = value;
        this.delimeter = delimeter;
    }

    public LObject<TList> getValue() {
        return value;
    }

    public void setValue(LObject<TList> value) {
        this.value = value;
    }

    public LObject getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(LObject delimeter) {
        this.delimeter = delimeter;
    }
    
    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        TList evaluatedValue = this.value.evaluate(bundle, field, value);
        Object[] resultValue = evaluatedValue.getValue();
        
        String resultDelimeter = delimeter != null ?
                delimeter.evaluate(bundle, field, value).toString() : null;
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < resultValue.length; i++) {
            if (i > 0 && resultDelimeter != null) {
                result.append(resultDelimeter);
            }
            if (resultValue[i] != null) {
                result.append(resultValue[i]);
            }
        }
        return TString.newInstance(result.toString());
    }

    @Override
    public Object[] params() {
        return delimeter == null ?
                new Object[] {value} :
                new Object[] {value, delimeter};
    }
}
