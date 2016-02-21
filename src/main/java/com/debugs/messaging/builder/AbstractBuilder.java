/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.builder;

import com.debugs.messaging.lang.LangArgumentsOutOfRangeException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public abstract class AbstractBuilder<T, A> {
    
    private final String name;
    private final List<A> arguments;

    public AbstractBuilder(String name) {
        this.name = name;
        arguments = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void addArgument(A argument) {
        arguments.add(argument);
    }

    protected abstract Class loadClass() throws ClassNotFoundException;
    
    private Object loadObject(Constructor con, Class[] classes) throws 
            InstantiationException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {
        int size = arguments.size();
        if (classes.length == 1 && classes[0].isArray()) {
            Object[] args = (Object[]) Array.newInstance(classes[0].getComponentType(), size);
            arguments.toArray(args);
            return con.newInstance(new Object[] {args});
        } else if (arguments.size() == classes.length) {
            for (int i = 0; i < size; i++) {
                if (!classes[i].isAssignableFrom(arguments.get(i).getClass())) {
                    return null;
                }
            }
            Object[] args = new Object[size];
            arguments.toArray(args);
            return con.newInstance(args);
        } else {
            return null;
        }
    }
    
    public T build() throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException,
            LangArgumentsOutOfRangeException,
            NoMatchingArgumentsException {
        Class target = loadClass();
        int size = arguments.size();
        if (size == 0) {
            return (T) target.newInstance();
        } else {
            Constructor[] cons = target.getConstructors();
            for (Constructor con : cons) {
                Class[] classes = con.getParameterTypes();
                Object result = loadObject(con, classes);
                if (result != null) {
                    return (T) result;
                }
            }
        }
        throw new NoMatchingArgumentsException(target);
    }
    
}
