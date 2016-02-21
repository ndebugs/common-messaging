/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.builder;

import com.debugs.messaging.type.TObject;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public class TypeBuilder extends AbstractBuilder<TObject, String> {
    
    public TypeBuilder(String name) {
        super(name);
    }
    
    protected Class loadClass() throws ClassNotFoundException {
        StringBuilder classPath = new StringBuilder(TObject.class.getPackage().getName())
                .append('.')
                .append(TObject.PREFIX_NAMESPACE)
                .append(getName());
        return Class.forName(classPath.toString());
    }
}
