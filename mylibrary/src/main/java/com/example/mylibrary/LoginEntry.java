package com.example.mylibrary;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginEntry implements Parcelable {
    private String userName;
    private String passWord;

    public LoginEntry() {
    }

    protected LoginEntry(Parcel in) {
        this.userName = in.readString();
        this.passWord = in.readString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int FLAG) {
        parcel.writeString(this.userName);
        parcel.writeString(this.passWord);
    }

    public void readFromParcel(Parcel in) {
        this.userName = in.readString();
        this.passWord = in.readString();
    }

    public static final Creator<LoginEntry> CREATOR = new Creator<LoginEntry>() {
        @Override
        public LoginEntry createFromParcel(Parcel in) {
            return new LoginEntry(in);
        }

        @Override
        public LoginEntry[] newArray(int size) {
            return new LoginEntry[size];
        }
    };
}
