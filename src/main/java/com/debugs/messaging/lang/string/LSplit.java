/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LSplit extends LObject<TList> {
    
    private LObject value;
    private LObject delimeter;

    public LSplit(LObject value, LObject delimeter) {
        this.value = value;
        this.delimeter = delimeter;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    public LObject getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(LObject delimeter) {
        this.delimeter = delimeter;
    }
    
    @Override
    public TList evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String valueResult = this.value.evaluate(bundle, field, value).toString();
        String delimeterResult = delimeter.evaluate(bundle, field, value).toString();
        
        List<String> list = new ArrayList();
        int from = 0;
        do {
            int to = valueResult.indexOf(delimeterResult, from);
            if (to == -1) {
                to = valueResult.length();
            }
            String val = valueResult.substring(from, to);
            list.add(val);
            from = to + delimeterResult.length();
        } while (from < valueResult.length());
        
        String[] result = new String[list.size()];
        list.toArray(result);
        return TList.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {value, delimeter};
    }
}
