package com.example.myapplication.factoryStrategy;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {
    /**
     * 策略集合
     */
    private Map<Integer, Fighting<Weapon>> strategySparseArray = new HashMap<>();

    /**
     * 初始化策略集合
     */
    {
        strategySparseArray.put(0, Weapon -> Weapon.closeAttract());
        strategySparseArray.put(1, Weapon -> Weapon.rangedAttract());
    }

    public Fighting<Weapon> getSparseArray(int key) {
        return strategySparseArray.get(key);
    }

    /**
     * 静态内部类实现holder模式单例，懒加载
     */
    static class StrategyInstance {

        @NonNull
        @Contract(" -> new")
        public static StrategyFactory getStrategyFactory() {
            return new StrategyFactory();
        }
    }
}
