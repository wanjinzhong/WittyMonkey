package com.wittymonkey.util;

import java.util.regex.Pattern;

/**
 * Created by neilw on 2017/2/23.
 */
public class Validator {
    /**
     * 验证邮箱格式是否正确
     *
     * @param email
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>true</td>
     * <td>邮箱格式正确</td>
     * </tr>
     * <tr>
     * <td>false</td>
     * <td>邮箱格式不正确</td>
     * </tr>
     * </table>
     */
    public static boolean validateEmail(String email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        return regex.matcher(email).matches();
    }

}
