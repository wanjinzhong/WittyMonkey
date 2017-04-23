package com.wittymonkey.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neilw on 2017/4/14.
 */
public class Constraint {
    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    public static final Integer USER_INCUMBENT = 0;
    public static final Integer USER_DIMISSORY = 1;

    public static final Integer FINANCE_TYPE_ALL = 2;
    public static final Integer FINANCE_TYPE_IN = 1;
    public static final Integer FINANCE_TYPE_OUT = 0;
    public static final Integer FINANCE_TYPE_DEFAULT_IN = 1;
    public static final Integer FINANCE_TYPE_DEFAULT_OUT = 2;
    public static final Integer FINANCE_TYPE_ROOM_IN = 3;
    public static final Integer FINANCE_TYPE_PURCHASE_OUT = 4;
    public static final Integer FINANCE_TYPE_CATERING_IN = 5;
    public static final Integer FINANCE_TYPE_SALARY_OUT = 6;

    public static final Integer FINANCE_ALL = 2;
    public static final Integer FINANCE_IN = 1;
    public static final Integer FINANCE_OUT = 0;

    public static final Integer REIMBURSE_STATUS_PENDING = 1;
    public static final Integer REIMBURSE_STATUS_PASSED = 2;
    public static final Integer REIMBURSE_STATUS_REJECTED = 3;
    public static final Integer REIMBURSE_OPT_PASSE = 1;
    public static final Integer REIMBURSE_OPT_REJECTE = 0;


    public static final Integer SALARY_SEARCH_CONDITION_HOTEL_ID = 1;
    public static final Integer SALARY_SEARCH_CONDITION_STAFF_NAME = 2;
    public static final Integer SALARY_SEARCH_CONDITION_STAFF_NO = 3;
    public static final Integer SALARY_SEARCH_CONDITION_FROM = 4;
    public static final Integer SALARY_SEARCH_CONDITION_TO = 5;
    public static final Integer SALARY_SEARCH_CONDITION_INCUMBENT = 6;

    public static final Integer MATERIEL_SEARCHTYPE_ALL = 1;
    public static final Integer MATERIEL_SEARCHTYPE_TYPE = 2;
    public static final Integer MATERIEL_SEARCHTYPE_WARN = 3;
    public static final Integer MATERIEL_SEARCH_CONDITION_HOTEL_ID = 1;
    public static final Integer MATERIEL_SEARCH_CONDITION_TYPE_ID = 2;
    public static final Integer MATERIEL_SEARCH_CONDITION_WARN = 3;

    public static final String LANG_ZH_CN = "zh_CN";
    public static final String LANG_EN_US = "en_US";
}
