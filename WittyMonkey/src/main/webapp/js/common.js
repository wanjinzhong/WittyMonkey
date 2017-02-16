/**
 * 验证酒店名称
 * @param nameInp
 * @returns {boolean}
 */
function validateHotelName(nameInp) {
    var name = $(nameInp).val();
    if (name == null || name == "") {
        layer.tips(messageOfValidateNull(regist_hotel_name), nameInp, {tips: 2});
        return false;
    }
    if (name.length > 50) {
        layer.tips(messageOfValidateLength(regist_hotel_name, 50), nameInp, {tips: 2});
        return false;
    }
    return true;
}
/**
 * 验证营业执照编号
 * @param inp
 */
function validateLicenseNo(inp) {
    var licenseNo = $(inp).val();
    if (licenseNo == null || licenseNo == "") {
        layer.tips(regist_validate_hotel_licenseNo_null, inp, {tips: 2});
        return false;
    }
    var reg = /(^\d{15}$)/;
    if (!reg.test(licenseNo)) {
        layer.tips(regist_validate_hotel_licenseNo_wrong, inp, {tips: 2});
        return false;
    }
    return true;
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
 * 验证登陆名
 * @param nameInp
 * @returns
 */
function validateLoginName(nameInp) {
    var name = $(nameInp).val();
    if (name.length <= 0) {
        layer.tips(messageOfValidateNull(regist_login_name), nameInp, {tips: 2});
        return false;
    }
    var reg = /^[a-zA-Z0-9_]{3,20}$/;
    if (!reg.test(name)) {
        layer.tips(regist_login_name_reg, nameInp, {tips: 2});
        return false;
    }
    var funResult;
    $.ajax({
        type: "GET",
        url: "validateLoginName.do",
        data: {"loginName": name},
        dataType: "json",
        // ajax 同步
        async: false,
        success: function (data) {
            var result = eval("(" + data + ")");
            if (result.status == 200) {
                layer.tips(regist_user_is_exist, nameInp, {tips: 2});
                funResult = false;
            } else {
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
/***
 * 验证密码
 * @param pwd
 * @returns
 */
function validatePassword(pwdInp) {
    var password = $(pwdInp).val();
    var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
    if (password.length <= 0) {
        layer.tips(messageOfValidateNull(regist_password), pwdInp, {tips: 2});
        return false;
    }
    if (!reg.test(password)) {
        layer.tips(regist_password_reg, pwdInp, {tips: 2});
        return false;
    } else {
        return true;
    }
}
/**
 * 验证重复密码
 * @param repwd
 * @returns
 */
function validateRepassword(repwdInp) {
    var password = $("#password").val();
    var repassword = $(repwdInp).val();
    if (password != repassword) {
        layer.tips(regist_password_not_same, repwdInp, {tips: 2});
        return false;
    } else {
        return true;
    }
}
/**
 * 验证邮箱
 * @param email
 * @returns
 */
function validateEmail(inp) {
    var email = $(inp).val();
    if (email.length <= 0) {
        layer.tips(messageOfValidateNull(regist_email), inp, {tips: 2});
        return false;
    }
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    if (!reg.test(email)) {
        layer.tips(regist_email_is_wrong, inp, {tips: 2});
        return false;
    }
    var funResult;
    $.ajax({
        url: "validateEmail.do",
        data: {"email": email},
        dataType: "json",
        async: false,
        success: function (data) {
            var result = eval("(" + data + ")");
            if (result.status == 400) {
                layer.tips(regist_email_is_wrong, inp, {tips: 2});
                funResult = false;
            } else if (result.status == 200) {
                layer.tips(regist_validate_email_exist, inp, {tips: 2});
                funResult = false;
            } else if (result.status == 201) {
                funResult = true;
            }
        }
    });
    return funResult;
}
/**
 * Ajax方式验证发至邮箱的验证码是否正确
 * <b>status:400:没有获取验证码；200：验证通过；401：验证码错误</b>
 * @param codeInp
 * @returns
 */
function validateCode(codeInp) {
    var code = $(codeInp).val();
    var codeBtn = $("#get_code");
    if (code.length <= 0) {
        layer.tips(messageOfValidateNull(regist_validate_code), codeInp, {tips: 2});
        return false;
    }
    var funResult;
    $.ajax({
        type: "GET",
        url: "validateEmailCode.do",
        data: {
            "code": code
        },
        dataType: "text",
        // ajax 同步
        async: false,
        success: function (data) {
            var result = eval("(" + data + ")");
            if (result.status == 400) {
                layer.tips(regist_get_code_first, codeBtn, {tips: 1});
                funResult = false;
            } else if (result.status == 401) {
                layer.tips(regist_code_is_wrong, codeInp, {tips: 2});
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
 * 验证法人名字长度
 * @param inp
 * @returns {boolean}
 */
function validateLegalName(inp) {
    var legalName = $(inp).val();
    if (legalName.length <= 0) {
        layer.tips(messageOfValidateNull(regist_legal_name), inp, {tips: 2});
        return false;
    }
    if (legalName.length > 20) {
        layer.tips(messageOfValidateLength(regist_legal_name, 20), inp, {tips: 2});
        return false;
    }
    return true;
}
/**
 * 验证具体地址
 * @param inp
 * @returns {boolean}
 */
function validatePlaceDetail(inp) {
    var place = $(inp).val();
    if (place.length > 255) {
        layer.tips(messageOfValidateLength(regist_place_detail, 255), inp, {tips: 2});
        return false;
    } else {
        return true;
    }
}
/**
 * 验证整个用户注册表单
 * @param form
 * @returns
 */
function validateRegistUserForm(form) {
    var loginName = $(form).find("#loginName");
    var realName = $(form).find("#realName");
    var pwd = $(form).find("#password");
    var repwd = $(form).find("#repassword");
    var email = $(form).find("#email");
    var idCard = $(form).find("#idCard");
    var code = $(form).find("#code");
    if (!validateLoginName(loginName)) {
        return false;
    }
    if (!validateRealName(realName)) {
        return false;
    }
    if (!validatePassword(pwd)) {
        return false;
    }
    if (!validateRepassword(repwd)) {
        return false;
    }
    if (!validateEmail(email)) {
        return false;
    }
    if (!validateIdCard(idCard)) {
        return false;
    }
    return true;
}
/**
 * 验证整个酒店注册表单
 * @param form
 * @returns {boolean}
 */
function validateRegistHotelForm(form) {
    var hotelName = $(form).find("#hotelName");
    var licenseNo = $(form).find("#licenseNo");
    var legalName = $(form).find("#legalName");
    var legalIdCard = $(form).find("#idCard");
    var place = $(form).find("#placeDetail");
    if (!validateHotelName(hotelName)) {
        return false;
    }
    if (!validateLicenseNo(licenseNo)) {
        return false;
    }
    if (!validateLegalName(legalName)) {
        return false;
    }
    if (!validateIdCard(legalIdCard)) {
        return false;
    }
    if (!validatePlaceDetail(place)) {
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
                if (result.status == 200){
                    layer.tips(floor_validate_no_exist, inp, {tips: 2});
                    funResult = false;
                } else if (result.status == 400){
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
 *  验证备注
 */
function validateNote(inp) {
    var note = $(inp).val();
    if (note.length > 1024) {
        layer.tips(messageOfValidateLength(note, 1024), inp, {tips: 2});
        return false;
    } else {
        return true;
    }
}
/**
 * 验证新增楼层
 * @param inp
 */
function validateAddFloor(form) {
    var floorNo = $(form).find("#floorNo");
    var note = $(form).find("#note");
    if (!validateFloorNo(floorNo)) {
        return false;
    } else if (!validateNote(note)) {
        return false;
    } else {
        return true;
    }
}
/**
 * 关闭弹窗
 */
function closeMe() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}

function formatDate(obj) {
    var time = new Date(obj);
    var year = time.getFullYear();
    var month = time.getMonth()+1;
    var date = time.getDate();
    var hour = time.getHours();
    var minutes = time.getMinutes();
    var second = time.getSeconds();

    //月，日，时，分，秒 小于10时，补0
    if(month<10){
        month = "0" + month;
    }
    if(date<10){
        date = "0" + date;
    }
    if(hour <10){
        hour = "0" + hour;
    }
    if(minutes <10){
        minutes = "0" + minutes;
    }
    if(second <10){
        second = "0" + second ;
    }
    return year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + second;
}