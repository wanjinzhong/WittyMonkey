package com.wittymonkey.util;

import java.math.BigDecimal;

/**
 * Created by neilw on 2017/4/30.
 */
public class NumberUtil {

    /**
     * 四舍五入保留n位小数
     * @param number 要操作的数
     * @param newScale 保留小数位数
     * @return
     */
    public static Double round(Double number,Integer newScale) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
