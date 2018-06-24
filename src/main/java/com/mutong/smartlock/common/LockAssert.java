package com.mutong.smartlock.common;

public class LockAssert
{
    public static void isTrue(boolean expression, String code,String msg) {
        if (!expression) {
            throw new LockException(code,msg);
        }
    }

    public static void isTrue(boolean expression, String code) {
        if (!expression) {
            throw new LockException(code);
        }
    }
}
