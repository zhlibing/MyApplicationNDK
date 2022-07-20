package com.example.myapplication.factoryStrategy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Optional;

public class factoryStrategyTest {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        System.out.println("使用近距离攻击策略");
        StrategyFactory.StrategyInstance.getStrategyFactory().getSparseArray(0).fight(new Knife());
        System.out.println("现在使用远距离攻击策略");
        StrategyFactory.StrategyInstance.getStrategyFactory().getSparseArray(1).fight(new Gun());
        Weapon weapon = new Knife();
        Optional.ofNullable(weapon).map(weapon1 -> new Gun()).ifPresent(weapon1 -> weapon.closeAttract());
    }

}
