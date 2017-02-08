# 数据字典
---
## 用户 (User)

 * 实体类名：`User`
 * 数据库字段：`user`
 * 说明：存储人员信息

|实体属性 |数据库字段|类型|说明|
|:----:|:----:|:----:|:----:|
|id|id|Integer/int|自增|
|realName|real_name|String/varchar|真实名字|
|loginName|login_name|String/varchar|登陆名（唯一）|
|password|password|String/varchar|密码|
|idCardNo|idcard_no|String/varchar|身份证号（可推出生日，年龄，性别）|
|tel|tel|String/varchar|电话|
|email|email|String/varchar|邮箱|
|registDate|regist_date|Date|注册时间|
|Hotel|hotel|Hotel/int|工作酒店，外键|
|isDimission|is_dimission|Integer/int|是否离职（0：未离职；1：已离职）|
|dimissionDesc|dimission_desc|String/varchar|离职原因|
|entryDatetime|entry_datetime|Date/datetime|操作时间|
|entryUser|entry_id|Date/datetime|操作人|
|roles|--|List&lt;Role&gt;|所属角色，多个|
|Setting|setting_id|Setting/int|设置，外键|

## 角色 (Role)

 * 实体类名：`Role`
 * 数据库字段：`role`
 * 说明：存储角色信息，如经理，前台，后勤等

|实体属性 |数据库字段|类型|说明|
|:----:|:----:|:----:|:----:|
|id|id|Integer/int|自增|

## 酒店（Hotel）

 * 实体类名：`Hotel`
 * 数据库字段：`hotel`
 * 说明：存储酒店宏观信息

|实体属性 |数据库字段|类型|说明|
|:----:|:----:|:----:|:----:|
|id|id|Integer/int|自增|
|name|name|String/varchar|酒店名字|
|area|area_code|Area/varchar|地区，外键。可由地区获得城市、省|
|place|place|String/varchar|具体地址|
|star|star|Integer/int|星级|
|tel|tel|String/varchar|电话|
|fax|fax|String/varchar|传真|
|email|email|String/varchar|邮箱|
|openDate|open_date|Date/datetime|开业时间|
|addDate|add_date|Date/datetime|加入系统时间|
|floors|--|List&lt;Floor&gt;|楼层，通过楼层可获得房间|
|entryDatetime|entry_datetime|Date/datetime|操作时间|
|entryUser|entry_id|Date/datetime|操作人|
