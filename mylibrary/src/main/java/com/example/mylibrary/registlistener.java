package com.example.mylibrary;

import android.content.Context;
import android.content.ServiceConnection;

public interface registlistener {
    void regist(Context context, ServiceConnection serviceConnection);

    void unRegist(Context context, ServiceConnection serviceConnection);
}