package com.example.mylibrary;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public class LoginManager implements registlistener {
    private volatile static LoginManager instance;

    /**
     * 双重校验锁单例
     * @return
     */
    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                    return instance;
                }
            }
        }
        return instance;
    }


    @Override
    public void regist(Context context, ServiceConnection serviceConnection) {
        Intent intent = new Intent();
        intent.setPackage("com.example.servicetest");
        intent.setAction("com.example.servicetest.testservice");
        context.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void unRegist(Context context, ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }
}
