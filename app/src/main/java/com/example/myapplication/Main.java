package com.example.myapplication;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.function.Function;

public class Main {
    ThreadLocal<Long> threadLocal = new ThreadLocal<Long>(){
        @Nullable
        @Override
        protected Long initialValue() {
            return super.initialValue();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String... args) {
        Main main = new Main();
        main.head();
    }

    public People say(String word, String key) {
        System.out.println("say>");
        return new People(word, key);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void listen(Function<String, People> function, String key) {
        System.out.println("listen>");
        People callback = function.apply("listen<<");
        System.out.println(callback.getSex() + ">>" + callback.getName() + "'''" + key);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void head() {
        listen(s -> say(s, "1"), "2");
    }
}
