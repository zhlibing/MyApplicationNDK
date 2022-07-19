package com.example.myapplication.factoryStrategy;

public class factoryStrategyTest {

    public static void main(String[] args) {
        System.out.println("使用近距离攻击策略");
        StrategyFactory.StrategyInstance.getStrategyFactory().getSparseArray(0).fight(new Knife());
        System.out.println("现在使用远距离攻击策略");
        StrategyFactory.StrategyInstance.getStrategyFactory().getSparseArray(1).fight(new Gun());
    }

}
