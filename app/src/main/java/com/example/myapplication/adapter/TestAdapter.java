package com.example.myapplication.adapter;

public class TestAdapter {
    public static void main(String[] args) {
        Socket threeHoleSocket = new ThreeHoleSocket(3);
        HoleAdapter holeAdapter = new HoleAdapter();
        Socket twoHoleSocket = holeAdapter.convert(threeHoleSocket);
        twoHoleSocket.provideElectric();
        threeHoleSocket.provideElectric();
    }
}
