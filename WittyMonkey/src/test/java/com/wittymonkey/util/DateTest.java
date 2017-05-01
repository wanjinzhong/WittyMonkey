package com.wittymonkey.util;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by neilw on 2017/3/9.
 */
public class DateTest {
    @Test
    public void dateDiff(){
        Date d1 = new Date(2017,3,9,16,53,28);
        System.out.println(DateUtil.lastMonthFirstDate(d1));
    }

    @Test
    public void addOneDay(){
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(d1));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(sdf.format(calendar.getTime()));
    }
}
