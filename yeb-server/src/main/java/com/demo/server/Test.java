package com.demo.server;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static String getEncryptionString(String password){

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
    public static void main(String[] args) {
        String password=Test.getEncryptionString("123");
        System.out.println(password);
    }
}
