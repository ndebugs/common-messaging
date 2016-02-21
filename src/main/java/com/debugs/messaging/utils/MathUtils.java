/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.utils;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public final class MathUtils {

    private MathUtils() {}
    
    public static int digit(int number) {
        if (number < 0) {
            number *= -1;
        }
        int count = 0;
        do {
            number /= 10;
            count++;
        } while (number > 0);
        return count;
    }
    
    public static int random(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
    
    public static double random(double min, double max) {
        return min + (Math.random() * (max - min + 1));
    }
}
