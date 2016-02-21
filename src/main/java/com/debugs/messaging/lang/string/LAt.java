/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.lang.string;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TInteger;
import com.debugs.messaging.type.TNumber;
import com.debugs.messaging.type.TObject;
import com.debugs.messaging.type.TString;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LAt extends LObject<TString> {

    private LObject source;
    private LObject index;
    private LObject length;

    public LAt(LObject source, LObject index) {
        this(source, index, null);
    }

    public LAt(LObject source, LObject index, LObject length) {
        this.source = source;
        this.index = index;
        this.length = length;
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

    public LObject getLength() {
        return length;
    }

    public void setLength(LObject length) {
        this.length = length;
    }

    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String resultSource = source.evaluate(bundle, field, value).toString();

        TObject evaluatedIndex = index.evaluate(bundle, field, value);
        TNumber parsedIndex = evaluatedIndex instanceof TNumber ?
                    (TNumber) evaluatedIndex :
                    TInteger.newInstance(evaluatedIndex.getValue());
        int resultIndex = parsedIndex.integerValue();

        String result;
        if (resultIndex < 0) {
            int resultLength;
            if (length != null) {
                TObject evaluatedLength = length.evaluate(bundle, field, value);
                TNumber parsedLength = evaluatedLength instanceof TNumber ?
                            (TNumber) evaluatedLength :
                            TInteger.newInstance(evaluatedLength.getValue());
                resultLength = parsedLength.integerValue();
            } else {
                resultLength = resultSource.length() + resultIndex + 1;
            }
            Integer reversedIndex = resultSource.length() - resultLength + resultIndex + 1;
            result = resultSource.substring(reversedIndex, reversedIndex + resultLength);
        } else if (length != null) {
            TObject evaluatedLength = length.evaluate(bundle, field, value);
            TNumber parsedLength = evaluatedLength instanceof TNumber ?
                        (TNumber) evaluatedLength :
                        TInteger.newInstance(evaluatedLength.getValue());
            int resultLength = parsedLength.integerValue();
            result = resultSource.substring(resultIndex, resultIndex + resultLength);
        } else {
            result = resultSource.substring(resultIndex);
        }
        return TString.newInstance(result);
    }

    @Override
    public Object[] params() {
        return length == null
                ? new Object[]{source, index}
                : new Object[]{source, index, length};
    }
}
