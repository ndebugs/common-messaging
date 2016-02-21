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
import com.debugs.messaging.type.TString;
import com.debugs.messaging.utils.Padder;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LPad extends LObject<TString> {
    
    private LObject value;
    private LObject character;
    private LObject length;

    public LPad(LObject value, LObject character, LObject length) {
        this.value = value;
        this.character = character;
        this.length = length;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    public LObject getCharacter() {
        return character;
    }

    public void setCharacter(LObject character) {
        this.character = character;
    }

    public LObject getLength() {
        return length;
    }

    public void setLength(LObject length) {
        this.length = length;
    }

    @Override
    public TString evaluate(MessageBundle bundle, Field field, Object value) throws Exception {
        String resultValue = this.value.evaluate(bundle, field, value).toString();
        
        TObject evaluatedCharacter = character.evaluate(bundle, field, value);
        TCharacter parsedCharacter = evaluatedCharacter instanceof TCharacter ?
                (TCharacter) evaluatedCharacter :
                TCharacter.newInstance(evaluatedCharacter.getValue());
        Character resultCharacter = parsedCharacter.getValue();
        
        TObject evaluatedLength = length.evaluate(bundle, field, value);
        TNumber parsedLength = evaluatedLength instanceof TNumber ?
                    (TNumber) evaluatedLength :
                    TInteger.newInstance(evaluatedLength.getValue());
        int resultLength = parsedLength.integerValue();
        
        boolean isRTL = resultLength < 0;
        Padder padder = new Padder(resultCharacter, isRTL);
        String result = padder.pad(resultValue, isRTL ? -resultLength : resultLength);
        return TString.newInstance(result);
    }
        
    @Override
    public Object[] params() {
        return new Object[] {value, character, length};
    }
}
