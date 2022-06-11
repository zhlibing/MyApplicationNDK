package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mylibrary.IMyAidlInterface;

public class ServiceTest extends Service {
    private String TAG = ServiceTest.class.getCanonicalName();
    MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
//        myBinder.linkToDeath(new IBinder.DeathRecipient() {
//            @Override
//            public void binderDied() {
//
//            }
//        },0);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d(TAG, anInt + ">" + aLong + ">" + aBoolean + ">" + aFloat + ">" + aDouble + ">" + aString);
            return "this is from server";
        }
    }
}
