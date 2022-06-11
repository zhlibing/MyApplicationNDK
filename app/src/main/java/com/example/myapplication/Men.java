package com.example.myapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Function;

public class Men {
    public String hight = "0";

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public Men(String hight) {
        this.hight = hight;
    }

    public String say(String word) {
        System.out.println("say()" + word);
        return word + ">>";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void listen(Function<String, String> function) {
        function.apply("listen");
        System.out.println("listen()");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void hear() {
        System.out.println("hear()");
        listen(s -> say("say" + s));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        Men men = new Men("ss");
        men.hear();
    }
}
