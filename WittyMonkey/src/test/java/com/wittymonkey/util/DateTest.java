package com.wittymonkey.util;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

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

}
