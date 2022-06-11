// IMyAidlInterface.aidl
package com.example.mylibrary;
import com.example.mylibrary.LoginEntry;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    LoginEntry login(inout LoginEntry loginEntry);
}