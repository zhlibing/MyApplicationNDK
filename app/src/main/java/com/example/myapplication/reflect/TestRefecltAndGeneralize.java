package com.example.myapplication.reflect;

import java.util.ArrayList;
import java.util.List;

public class TestRefecltAndGeneralize {
    public static void main(String[] args) {
        List<HIDL> hidls = new ArrayList<>();
        hidls.add(BMWFactory.getFactory().manufactured());
        hidls.add(PorscheFactory.getFactory().manufactured());
        UtilTest.Instance.byCar(hidls);
    }
}
