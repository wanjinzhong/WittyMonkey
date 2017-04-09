/**
 * Created by neilw on 2017/2/20.
 */
/**
 * 关闭弹窗
 */

function closeMe() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}
/**
 * 刷新页面
 */
function reload() {
    window.location.reload();
}
/**
 * 日期格式化
 */
function formatDate(obj) {
    var time = new Date(obj);
    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    var date = time.getDate();
    var hour = time.getHours();
    var minutes = time.getMinutes();
    var second = time.getSeconds();

    //月，日，时，分，秒 小于10时，补0
    if (month < 10) {
        month = "0" + month;
    }
    if (date < 10) {
        date = "0" + date;
    }
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (second < 10) {
        second = "0" + second;
    }
    return year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + second;
}

/**
 * 验证备注
 * @param inp
 */
function validateNote(inp) {
    var note = $(inp).val();
    if (note.length > 1024) {
        layer.tips(messageOfValidateLength(message_note, 1024), inp, {tips: 2});
        return false;
    } else {
        return true;
    }
}
/**
 * 分页
 * @param curr
 */
function page(url, curr, condition) {
    if (curr == undefined) {
        curr = 1;
    }
    if (condition == undefined) {
        condition = {"curr": curr};
    } else {
        condition["curr"] = curr;
    }
    $.ajax({
        type: "GET",
        url: url,
        data: condition,
        dataType: "json",
        success: function (data) {
            var res = eval("(" + data + ")");
            var pageSize = res["pageSize"];
            laypage({
                cont: "page",
                pages: Math.ceil(res["count"] / pageSize),
                curr: curr || 1,
                first: page_first,
                last: page_last,
                prev: page_prev,
                next: page_next,
                skip: true,
                jump: function (obj, first) {
                    if (!first) {
                        page(url, obj.curr, condition);
                    }
                    refreshTable(res["data"]);
                }
            });
        }
    });
}

function validateLength(inp, name, length) {
    var content = $(inp).val();
    if (content.length > content){
        layer.tips(messageOfValidateLength(name, length), {tips: 2});
        return false;
    }
    return true;
}

/**
 * 验证楼层号
 * @param inp
 * @returns {boolean}
 */
function validateFloorNo(method, inp) {
    var no = $(inp).val();
    var reg = /^-?\d{1,3}$/;
    if (!reg.test(no)) {
        layer.tips(floor_validate_no_wrong, inp, {tips: 2});
        return false;
    } else {
        var funResult;
        $.ajax({
            type: "GET",
            url: "validateFloorNo.do",
            data: {"floorNo": no, "method": method},
            dataType: "json",
            async: false,
            success: function (data) {
                var result = eval("(" + data + ")");
                if (result.status == 200) {
                    layer.tips(floor_validate_no_exist, inp, {tips: 2});
                    funResult = false;
                } else if (result.status == 400) {
                    layer.tips(floor_validate_no_wrong, inp, {tips: 2});
                    funResult = false;
                } else {
                    funResult = true;
                }
            }
        });
        return funResult;
    }

}

/**
 * 验证物料类型名称
 * @param inp
 * @returns {boolean}
 */
function validateMaterielTypeName(method, inp) {
    var name = $(inp).val();
    var funResult;
    if (name.length <= 0){
        layer.tips(messageOfValidateNull(name), inp, {tips: 2});
        return false;
    } else if (name.length > 50){
        layer.tips(messageOfValidateLength(name, 50), inp, {tips: 2});
        return false;
    }
    $.ajax({
        type: "GET",
        url: "validateMaterielTypeName.do",
        data: {"name": name, "method": method},
        dataType: "json",
        async: false,
        success: function (data) {
            var result = eval("(" + data + ")");
            if (result.status == 200) {
                layer.tips(materiel_type_validate_name_exist, inp, {tips: 2});
                funResult = false;
            } else {
                funResult = true;
            }
        }
    });
    return funResult;
}

/**
 * 验证身份证号
 * @param inp
 */
function validateIdCard(inp) {
    var idCard = $(inp).val();
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (idCard.length <= 0) {
        layer.tips(messageOfValidateNull(regist_idCard), inp, {tips: 2});
        return false;
    }
    if (!reg.test(idCard)) {
        layer.tips(regist_validate_common_idcard_wrong, inp, {tips: 2})
        return false;
    }
    var funResult;
    $.ajax({
        url: "validateIdCard.do",
        type: "GET",
        data: {"idCard": idCard},
        dataType: "json",
        // ajax 同步
        async: false,
        success: function (data) {
            var result = eval("(" + data + ")");
            if (result.status == 400) {
                layer.tips(regist_validate_common_idcard_wrong, inp, {tips: 2})
                funResult = false;
            } else if (result.status == 200) {
                funResult = true;
            }
        },
        error: function (data) {
            layer.msg(error_500, {time: 3000, icon: 5});
            funResult = false;
        }
    });
    return funResult;
}


/**
 * 验证真实姓名
 * @param inp
 * @returns {boolean}
 */
function validateRealName(inp) {
    var realName = $(inp).val();
    if (realName.length <= 0) {
        layer.tips(messageOfValidateNull(regist_real_name), inp, {tips: 2});
        return false;
    }
    if (realName.length > 20) {
        layer.tips(messageOfValidateLength(regist_real_name, 20), inp, {tips: 2});
        return false;
    }
    return true;
}
/**
 * 验证电话
 * @param inp
 * @returns {boolean}
 */
function validateTel(inp) {
    var tele = $(inp).val();
    if (tele.length <= 0) {
        return true;
    }
    if (tele.length > 20) {
        layer.tips(messageOfValidateLength(tel, 20), inp, {tips: 2});
        return false;
    }
    return true;
}
/**
 * 验证金额
 */
function validateMoney(inp) {
    var deposit = $(inp).val();
    var reg = /^[0-9]+(\.[0-9]+)?$/;
    if (deposit.length <= 0) {
        layer.tips(messageOfValidateNull(money), inp, {tips: 2});
        return false;
    }
    if (!reg.test(deposit)) {
        layer.tips(money_wrong, inp, {tips: 2});
        return false;
    }
    return true;
}

function validateMoneyValue(money) {
    var reg = /^[0-9]+(\.[0-9]+)?$/;
    if (money.length <= 0) {
        return false;
    }
    if (!reg.test(money)) {
        return false;
    }
    return true;
}

/**
 *计算天数差的函数，通用
 */
function dateDiff(from, to) {    //sDate1和sDate2是2006-12-18格式
    if (from == undefined || to == undefined) {
        return 0;
    }
    if (from instanceof Date) {
        from = from.format("yyyy-MM-dd");
    }
    var fromDate = new Date(from.replace(/-/g, "/"));
    var toDate = new Date(to.replace(/-/g, "/"));
    return parseInt(Math.abs(toDate - fromDate) / 1000 / 60 / 60 / 24)    //把相差的毫秒数转换为天数;
}
/**
 * 日期格式化
 * @param fmt
 * @returns {*}
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}