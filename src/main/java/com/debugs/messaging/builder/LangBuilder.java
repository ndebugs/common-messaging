/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.builder;

import com.debugs.messaging.lang.LObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class LangBuilder extends AbstractBuilder<LObject, LObject> {

    public LangBuilder(String name) {
        super(name);
    }

    @Override
    protected Class loadClass() throws ClassNotFoundException {
        String name = getName().replace(LObject.FUNCTION_DELIMETER, '.');
        int index = name.lastIndexOf(".") + 1;
        
        char[] cKey = name.toCharArray();
        cKey[index] = (char) (cKey[index] - 32);
        StringBuilder classPath = new StringBuilder(LObject.class.getPackage().getName())
                .append('.')
                .append(cKey);
        classPath.insert(classPath.length() - name.length() + index, LObject.PREFIX_NAMESPACE);
        return Class.forName(classPath.toString());
    }
}
