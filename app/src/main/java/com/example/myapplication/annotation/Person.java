package com.example.myapplication.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Description(author = "王五", desc = "他是个演员", age = 30)
public class Person<T extends Father, R extends String, Q extends Integer> {
    private T t;
    private R r;
    private Q q;

    @Work(parms = {"武汉", "工人体育馆"})
    public void actor() {
    }

    public static void main(String[] args) throws Exception {
        Person person = new Person<>();
        person.t = new Father();
        person.r = "666";
        person.q = 11;
        person.actor();
        Class c = Class.forName("com.example.myapplication.annotation.Person");
        Annotation[] annotations = c.getAnnotations();
        if (annotations.length > 0) {
            for (Annotation a : annotations) {
                if (a instanceof Description) {
                    Description description = (Description) a;
                    System.out.println(description.author() + description.desc() + description.age());
                }
            }
        }
        Method[] methods = c.getMethods();
        for (Method m : methods) {
            Annotation[] annotations1 = m.getAnnotations();
            for (Annotation a : annotations1) {
                System.out.println(a.annotationType());
                if (a instanceof Work) {
                    Work work = (Work) a;
                    for (String s : work.parms()) {
                        System.out.println(s);
                    }
                }
            }
        }
        System.out.println(person.t);
        System.out.println(person.r);
        System.out.println(person.q);
    }
}
