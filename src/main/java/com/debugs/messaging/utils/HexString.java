/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.debugs.messaging.utils;

/**
 *
 * @author van de Bugs <van.de.bugs@gmail.com>
 */
public final class HexString {

    private HexString() {}

    public static char toChar(int value) {
        if (value >= 0 && value < 10) {
            return (char) ('0' + value);
        } else if (value < 16) {
            return (char) ('A' + value - 10);
        } else {
            throw new IllegalArgumentException("'" + value + "' is not a valid hexadecimal value.");
        }
    }

    public static int toInt(char value) {
        if (value >= '0' && value <= '9') {
            return value - '0';
        } else if (value >= 'A' && value <= 'F') {
            return value - 'A' + 10;
        } else if (value >= 'a' && value <= 'f') {
            return value - 'a' + 10;
        } else {
            throw new IllegalArgumentException("'" + value + "' is not a valid hexadecimal character.");
        }
    }

    public static String encode(byte[] data) {
        return encode(data, 0);
    }

    public static String encode(byte[] data, int offset) {
        return encode(data, offset, data.length - offset);
    }

    public static String encode(byte[] data, int offset, int length) {
        char[] chars = new char[length * 2];
        for (int i = 0; i < length; i++) {
            int index = i * 2;
            int n = data[offset + i] & 0xff;
            chars[index] = toChar(n >>> 4);
            chars[index + 1] = toChar(n & 0x0f);
        }
        return new String(chars);
    }

    public static byte[] decode(String data) {
        return decode(data, 0);
    }

    public static byte[] decode(String data, int offset) {
        return decode(data, offset, data.length() - offset);
    }

    public static byte[] decode(String data, int offset, int length) {
        byte[] result = new byte[length / 2];
        int resultIndex = result.length - 1;
        for (int i = 0; i < length; i++) {
            int dataIndex = length - i - 1;
            if (i % 2 == 0) {
                result[resultIndex] = (byte) toInt(data.charAt(dataIndex));
            } else {
                result[resultIndex--] |= (byte) (toInt(data.charAt(dataIndex)) << 4);
            }
        }
        return result;
    }
}
