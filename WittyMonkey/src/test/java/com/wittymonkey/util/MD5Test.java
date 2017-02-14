package com.wittymonkey.util;

import org.junit.Test;

/**
 * Created by neilw on 2017/2/14.
 */
public class MD5Test {

    @Test
    public void md5Test(){
        System.out.println(MD5Util.encrypt("123456"));
        System.out.println(MD5Util.encrypt("123457"));
        System.out.println("8Yh9P55u56Mv5edvSrgNYw==".length());
    }
}
