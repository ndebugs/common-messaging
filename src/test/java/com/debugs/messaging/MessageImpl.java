/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class MessageImpl implements Message<String, Object> {

    private Map<String, Object> values;

    public MessageImpl() {
        values = new HashMap();
    }
    
    public Object get(String key) {
        return values.get(key);
    }

    public void set(String key, Object value) {
        values.put(key, value);
    }

    public List<String> keys() {
        return new ArrayList<String>(values.keySet());
    }
}
