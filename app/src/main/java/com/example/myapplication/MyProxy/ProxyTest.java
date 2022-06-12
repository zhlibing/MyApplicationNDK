package com.example.myapplication.MyProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    Animal animal;

    public static void main(String[] args) {
        ProxyTest proxyTest = new ProxyTest();
        proxyTest.testProxy();
    }

    void testProxy() {
        Dog dog = new Dog();
        /**
         * 动态代理
         */
        animal = (Animal) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Animal.class}, new MyHandler(dog));
        animal.run("10 km");
        String s = animal.speak("叽叽叽");
        System.out.println(s + "<<<");
        /**
         * 静态代理
         */
        StaticProxy staticProxy = new StaticProxy(dog);
        staticProxy.run("静态代理 1100km");
        String s2 = staticProxy.speak("汪汪汪");
        System.out.println(s2 + "<<<");
    }

    class MyHandler implements InvocationHandler {
        Dog dog;

        public MyHandler(Dog dog) {
            this.dog = dog;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            System.out.println("invoke");
            Object o1 = method.invoke(dog, objects);
            return o1;
        }
    }
}
