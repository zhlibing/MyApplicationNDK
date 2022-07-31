package com.example.myapplication.adapter;

public class HoleAdapter extends BaseAdapter {
    @Override
    public Socket convert(Socket socket) {
        if (socket instanceof TwoHoleSocket) {
            System.out.println("转换两孔插座到三孔");
            return new ThreeHoleSocket(3);
        } else if (socket instanceof ThreeHoleSocket) {
            System.out.println("转换三孔插座到两孔");
            return new TwoHoleSocket(2);
        }
        return new Socket(-1) {
            @Override
            void provideElectric() {

            }
        };
    }
}
