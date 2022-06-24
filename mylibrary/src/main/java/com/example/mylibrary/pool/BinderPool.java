package com.example.mylibrary.pool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.mylibrary.IBinderPool;

import java.util.concurrent.CountDownLatch;

public class BinderPool {

    public static final int QUERY_CODE_BINDER_A = 1;

    public static final int QUERY_CODE_BINDER_B = 2;

    private static Context mContext;

    private CountDownLatch countDownLatch;

    private IBinderPool binderPool;

    public static BinderPool getInstance(Context context) {
        mContext = context.getApplicationContext();
        return BinderPoolHolder.INSTANCE;
    }

    private BinderPool() {
        connectBinderPoolService();
    }

    private synchronized void connectBinderPoolService() {
        countDownLatch = new CountDownLatch(1);
        Intent intent = new Intent();
//        intent.setPackage("com.example.servicetest");
//        intent.setAction("com.example.servicetest.testservice");
        intent.setPackage("com.example.servicetest");
        try {
            intent.setClass(mContext, Class.forName("com.example.servicetest.ServiceTest"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mContext.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public IBinder queryBinder(int queryCode) {
        IBinder binder = null;
        if (binderPool != null) {
            try {
                binder = binderPool.queryBinder(queryCode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return binder;
    }

    private static class BinderPoolHolder {
        static final BinderPool INSTANCE = new BinderPool();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderPool = IBinderPool.Stub.asInterface(service);
            try {
                binderPool.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            binderPool.asBinder().unlinkToDeath(deathRecipient, 0);
            binderPool = null;
            connectBinderPoolService();
        }
    };

}