package com.example.myapplication.systemservice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class AccountInfo {
    Context context;

    public Context getContext() {
        return context;
    }

    void getAccount() {
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
