package com.example.mylibrary.pool;

import android.os.RemoteException;

import com.example.mylibrary.IBinderB;

public class BinderB extends IBinderB.Stub {
    @Override
    public String getInfo(String info) throws RemoteException {
        return info + "-handled";
    }
}