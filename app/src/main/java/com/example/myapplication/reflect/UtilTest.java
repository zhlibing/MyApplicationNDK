package com.example.myapplication.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public enum UtilTest {
    Instance;

    /**
     * 枚举自动给默认构造加了private属性，无法new
     */
    UtilTest() {
    }

    /**
     * 反射获取实现了接口的对象的属性和调用他的实现方法
     *
     * @param list
     * @param <T>
     */
    public <T> void byCar(List<T> list) {
        try {
            for (T t : list
            ) {
                Class clz = t.getClass();
                Object object = clz.newInstance();
                Method[] methods = clz.getMethods();
                for (Method m : methods
                ) {
                    System.out.println("方法名称：" + m.getName());
                }
                Method method = clz.getMethod("manufactory");
                String ss = (String) method.invoke(object);
                System.out.println("我知道他的生产厂是：:" + ss);

                Method method2 = clz.getMethod("maxSpeed");
                int maxSpeed = (int) method2.invoke(object);
                System.out.println("我知道他的最大速度是：:" + maxSpeed);

                Method method3 = clz.getMethod("run");
                method3.invoke(object);

                Method method4 = clz.getMethod("numbers");
                method4.invoke(object);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
