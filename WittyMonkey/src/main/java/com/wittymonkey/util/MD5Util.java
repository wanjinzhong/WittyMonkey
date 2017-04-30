package com.wittymonkey.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码MD5+Base64加密
 * Created by neilw on 2017/2/14.
 */
public class MD5Util {
    public static String encrypt(String str) {
        String result = null;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
            byte[] md5 = digest.digest(str.getBytes());
            result = Base64.encode(md5);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
