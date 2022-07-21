package com.example.myapplication.systemservice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class AccountInfo {

    private int CODE = new Random().nextInt(10);

    private static ConcurrentHashMap<Integer, AccountInfo> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 容器式单例
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static AccountInfo getInstance() {
        return concurrentHashMap.computeIfAbsent(getInstance().CODE, instance -> new AccountInfo());
    }


    public void getAccount(Context context) {
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        accountManager.addOnAccountsUpdatedListener(accounts -> {
            if (accounts.length > 0) {
                for (Account a : accounts) {
                    System.out.println(a.name);
                }
            }
        }, new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        }, true);
    }
}
