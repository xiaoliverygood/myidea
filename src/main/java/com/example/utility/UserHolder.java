package com.example.utility;

import com.example.model.LoginAuth;

import java.util.HashMap;
import java.util.Map;

public class UserHolder {
//    private static final ThreadLocal<LoginAuth> tl = new ThreadLocal<>();
//
//    public static void saveUser(LoginAuth loginAuth){
//        tl.set(loginAuth);
//    }
//
//    public static LoginAuth getUser(){
//        return tl.get();
//    }
//
//    public static void removeUser(){
//        tl.remove();
//    }

    private static final Map<String, Object> map = new HashMap<>();

    public static <T> void saveUser(String token ,T t) {
        map.put(token, t);
    }

    public static Object get(String token) {
        return map.get(token);
    }

    public static void remove(String token) {
        map.remove(token);
    }
}