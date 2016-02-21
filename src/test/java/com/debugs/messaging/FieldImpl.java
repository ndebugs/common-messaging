/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging;

import com.debugs.messaging.attribute.Filter;
import com.debugs.messaging.attribute.Length;
import com.debugs.messaging.attribute.Required;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class FieldImpl extends Field {

    private Required required;
    private Length length;
    private Filter filter;

    public Required getRequired() {
        return required;
    }

    public void setRequired(Required required) {
        this.required = required;
    }

    public Length getLength() {
        return length;
    }

    public void setLength(Length length) {
        this.length = length;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
