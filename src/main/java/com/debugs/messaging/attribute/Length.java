/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.attribute;

import com.debugs.messaging.Field;
import com.debugs.messaging.MessageBundle;
import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class Length extends Attribute {
    
    private boolean fixed;
    private int minValue;
    private int value;

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Void evaluate(MessageBundle bundle, Field field, TObject value) throws Exception {
        Object typeValue = value.getValue();
        if (typeValue != null) {
            int len = value.size();
            if ((fixed ? this.value != len : (this.value != -1 && this.value < len)) || len < minValue) {
                throw new LengthValidationException(this, typeValue);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString())
                .append("[fixed=").append(fixed)
                .append(", minValue=").append(minValue)
                .append(", value=").append(value)
                .append(']');
        return sb.toString();
    }
}
