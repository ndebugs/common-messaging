/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TCharacter;
import com.debugs.messaging.type.TInteger;
import com.debugs.messaging.type.TNumber;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LCharAt extends LObject<TCharacter> {
    
    private LObject source;
    private LObject index;

    public LCharAt(LObject source, LObject index) {
        this.source = source;
        this.index = index;
    }

    public LObject getSource() {
        return source;
    }

    public void setSource(LObject source) {
        this.source = source;
    }

    public LObject getIndex() {
        return index;
    }

    public void setIndex(LObject index) {
        this.index = index;
    }

    @Override
    public TCharacter evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String resultSource = source.evaluate(bundle, field, value).toString();
        
        TObject evaluatedIndex = index.evaluate(bundle, field, value);
        TNumber parsedIndex = evaluatedIndex instanceof TNumber ?
                    (TNumber) evaluatedIndex :
                    TInteger.newInstance(evaluatedIndex.getValue());
        int resultIndex = parsedIndex.integerValue();
        
        char result = resultSource.charAt(resultIndex);
        return TCharacter.newInstance(result);
    }

    @Override
    public Object[] params() {
        return new Object[] {source, index};
    }
}
