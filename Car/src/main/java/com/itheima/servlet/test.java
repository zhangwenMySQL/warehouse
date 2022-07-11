package com.itheima.servlet;

import java.io.File;

public class test {
    public static void main(String[] args) {
        File file=new File("C:\\Users\\26048\\Desktop"+"\\"+"test");
        if (!file.exists()){
            file.mkdir();
        }

    }
}
