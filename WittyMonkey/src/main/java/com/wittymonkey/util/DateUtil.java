package com.wittymonkey.util;

import java.util.Date;

/**
 * Created by neilw on 2017/3/9.
 */
public class DateUtil {
    public static Integer dateDiffDays(Date d1, Date d2){
        return (int) Math.ceil((d2.getTime() - d1.getTime())*1.0/(1000*60*60*24));
    }
}
