package com.example.mylibrary.pool;

import android.os.RemoteException;

import com.example.mylibrary.IBinderA;

public class BinderA extends IBinderA.Stub {
    @Override
    public int calc(int a, int b) throws RemoteException {
        return a + b;
    }
}
