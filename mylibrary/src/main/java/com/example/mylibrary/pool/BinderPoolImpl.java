package com.example.mylibrary.pool;

import android.os.IBinder;
import android.os.RemoteException;

import com.example.mylibrary.IBinderPool;

public class BinderPoolImpl extends IBinderPool.Stub {

    @Override
    public IBinder queryBinder(int queryCode) throws RemoteException {
        IBinder binder = null;
        switch (queryCode) {
            case BinderPool.QUERY_CODE_BINDER_A:
                binder = new BinderA();
                break;
            case BinderPool.QUERY_CODE_BINDER_B:
                binder = new BinderB();
                break;
            default:
                break;
        }
        return binder;
    }
}
