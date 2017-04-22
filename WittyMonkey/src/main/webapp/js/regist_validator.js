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
        return true;
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
    var realName = $(form).find("#realName");
    var pwd = $(form).find("#password");
    var repwd = $(form).find("#repassword");
    var email = $(form).find("#email");
    var idCard = $(form).find("#idCard");
    var code = $(form).find("#code");
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
