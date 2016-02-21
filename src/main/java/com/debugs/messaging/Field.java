/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging;

import com.debugs.messaging.attribute.Attribute;
import com.debugs.messaging.attribute.Filter;
import com.debugs.messaging.attribute.Length;
import com.debugs.messaging.attribute.Required;
import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.type.TObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class Field<K> {
    
    private K id;
    private TObject type;
    private LObject value;
    private Map<Object, Field> childs;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    public TObject getType() {
        return type;
    }

    public void setType(TObject type) {
        this.type = type;
    }

    public LObject getValue() {
        return value;
    }

    public void setValue(LObject value) {
        this.value = value;
    }

    public abstract Required getRequired();

    public abstract void setRequired(Required required);

    public abstract Length getLength();

    public abstract void setLength(Length length);

    public abstract Filter getFilter();

    public abstract void setFilter(Filter filter);

    public Map<Object, Field> getChilds() {
        return childs;
    }

    public void setChilds(Map<Object, Field> childs) {
        this.childs = childs;
    }
    
    public List<Attribute> attributes() {
        List<Attribute> list = new ArrayList();
        list.add(getRequired());
        list.add(getLength());
        list.add(getFilter());
        return list;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString())
                .append("[id=").append(id);
        for (Attribute attribute : attributes()) {
            if (attribute != null) {
                sb.append(", ")
                        .append(attribute.name())
                        .append("=")
                        .append(attribute.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
