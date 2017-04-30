package com.wittymonkey.vo;

import java.util.Date;


public class IDCardInfo {
    private String province;
    private String city;
    private String area;
    private Date birthday;
    private String sex;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "IDCardInfo [province=" + province + ", city=" + city
                + ", area=" + area + ", birthday=" + birthday + ", sex=" + sex
                + "]";
    }

}
