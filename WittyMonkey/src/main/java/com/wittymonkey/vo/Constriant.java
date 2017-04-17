package com.wittymonkey.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neilw on 2017/4/14.
 */
public class Constriant {
    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final Integer FINANCE_TYPE_ALL = 2;
    public static final Integer FINANCE_TYPE_IN = 1;
    public static final Integer FINANCE_TYPE_OUT = 0;
    public static final Integer FINANCE_ALL = 2;
    public static final Integer FINANCE_IN = 1;
    public static final Integer FINANCE_OUT = 0;
    public static final Integer FINANCE_TYPE_DEFAULT_IN = 1;
    public static final Integer FINANCE_TYPE_DEFAULT_OUT = 2;
    public static final Integer FINANCE_TYPE_ROOM_IN = 3;
    public static final Integer FINANCE_TYPE_PURCHASE_OUT = 4;
    public static final Integer FINANCE_TYPE_CATERING_IN = 5;
    public static final Integer FINANCE_TYPE_SALARY_OUT = 6;

    public static final Map<Integer, Map<String, String>> fiananceTypeI18n = new HashMap<Integer, Map<String, String>>();

    static {
        Map<String, String> defaultIn = new HashMap<String, String>();

    }
}
