/**
 * Created by neilw on 2017/2/20.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
});

/**
 * 验证房间号
 */
function validateRoomNo(method, inp) {
    var roomNo = $(inp).val();
    var result;
    if (roomNo.length <= 0) {
        layer.tips(messageOfValidateNull(room_no), inp, {tips: 2});
        return false;
    } else if (roomNo.length > 10) {
        layer.tips(messageOfValidateLength(room_no, 10), inp, {tips: 2});
        return false;
    } else {
        $.ajax({
            url: "validateRoomNo.do",
            data: {"roomNo": roomNo, 'method': method},
            async: false,
            dataType: "json",
            success: function (data) {
                var res = eval("(" + data + ")");
                switch (res.status) {
                    case 200:
                        layer.tips(room_validate_roomNo_exist, inp, {tips: 2});
                        result = false;
                        break;
                    case 201:
                        result = true;
                        break;
                    case 400:
                        layer.tips(messageOfValidateNull(room_no), inp, {tips: 2});
                        result = false;
                        break;
                    case 410:
                        layer.tips(messageOfValidateLength(10), inp, {tips: 2});
                        result = false;
                        break;
                }
            }
        });
        return result;
    }

}
/**
 * 验证房间名
 * @param inp
 * @returns {boolean}
 */
function validateRoomName(inp) {
    var roomName = $(inp).val();
    if (roomName.length > 20) {
        layer.tips(messageOfValidateLength(room_name, 20), inp, {tips: 2});
        return false;
    } else {
        return true;
    }
}
/**
 * 验证床位
 * @param inp
 * @returns {boolean}
 */
function validateBed(inp) {
    var bed = $(inp).val();
    var reg = /^[0-9]$/;
    if (!reg.test(bed)) {
        layer.tips(room_validate_bed, inp, {tips: 2});
        return false;
    } else {
        calcAvailableNum();
        return true;
    }
}
/**
 * 计算可住人数
 */
function calcAvailableNum() {
    var reg = /^[0-9]$/;
    var single = $("#singleBedNum").val();
    var double = $("#doubleBedNum").val();
    var num = 0;
    if (reg.test(single)) {
        num += Number(single);
    }
    if (reg.test(double)) {
        num += Number(double) * 2;
    }
    $("#availableNum").val(num);
}
/**
 * 验证可住人数
 * @param inp
 */
function validateAvailable(inp) {
    var available = $(inp).val();
    var reg = /^([0-9]|[1-9]\d|100)$/
    if (available.length <= 0) {
        layer.tips(messageOfValidateNull(room_availabel_num), inp, {tips: 2});
        return false;
    } else if (!reg.test(available)) {
        layer.tips(room_validate_availabel, inp, {tips: 2});
        return false;
    } else {
        return true;
    }
}
/**
 * 验证其它设施
 */
function validateOtherFacility(inp) {
    var other = $(inp).val();
    if (other.length > 1024) {
        layer.tips(messageOfValidateLength(room_other_facility, 1024), inp, {tips: 2});
        return false;
    } else {
        return true;
    }
}

/**
 * 验证面积
 * @param inp
 */
function validateArea(inp) {
    var reg = /^\d*\.?\d*$/;
    var area = $(inp).val();
    if (!reg.test(area)) {
        layer.tips(room_validate_area, inp, {tips: 2});
        return false;
    } else {
        return true;
    }
}

/**
 * 验证价格
 * @param inp
 */
function validatePrice(inp) {
    var reg = /^\d*\.?\d*$/;
    var price = $(inp).val();
    if (!reg.test(price)) {
        layer.tips(room_validate_price, inp, {tips: 2});
        return false;
    } else {
        return true;
    }
}


function save(method) {
    if (!validateRoomNo(method, $("#roomNum"))) {
        return false;
    } else if (!validateRoomName($("#roomName"))) {
        return false;
    } else if (!validateBed($("#singleBedNum"))) {
        return false;
    } else if (!validateBed($("#doubleBedNum"))) {
        return false;
    } else if (!validateAvailable($("#availableNum"))) {
        return false;
    } else if (!validateOtherFacility($("#otherFacility"))) {
        return false;
    } else if (!validateNote($("#note"))) {
        return false;
    } else {
        var index = layer.load();
        $.ajax({
            url: "saveRoom.do",
            type: "GET",
            data: $("#room-form").serialize(),
            dataType: "json",
            success: function (data) {
                layer.close(index);
                var res = eval("(" + data + ")");
                switch (res.status) {
                    case 400:
                        layer.tips(room_validate_roomNo_exist, $("#roomNum"), {tips: 2});
                        return false;
                    case 401:
                        layer.tips(messageOfValidateNull(room_no), $("#roomNum"), {tips: 2});
                        return false;
                    case 402:
                        layer.tips(messageOfValidateLength(room_no, 10), $("#roomNum"), {tips: 2});
                        return false;
                    case 410:
                        layer.tips(messageOfValidateLength(room_name, 20), $("#roomName"), {tips: 2});
                        return false;
                    case 420:
                        layer.tips(room_validate_bed, $("#singleBedNum"), {tips: 2});
                        return false;
                    case 430:
                        layer.tips(room_validate_bed, $("#doubleBedNum"), {tips: 2});
                        return false;
                    case 440:
                        layer.tips(room_validate_availabel, $("#availableNum"), {tips: 2});
                        return false;
                    case 450:
                        layer.tips(room_validate_floor_not_exist, $("#floorNo"), {tips: 2});
                        return false;
                    case 460:
                        layer.tips(room_validate_area, $("#area"), {tips: 2});
                        return false;
                    case 470:
                        layer.tips(room_validate_price, $("#price"), {tips: 2});
                        return false;
                    case 480:
                        layer.tips(messageOfValidateLength(room_other_facility, 1024), $("#otherFacility"), {tips: 2});
                        return false;
                    case 490:
                        layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});
                        return false;
                    case 200:
                        if (method == "add"){
                            layer.msg(room_add_success, {
                                icon: 1,
                                time: 2000
                            }, function () {
                                parent.location.reload();
                                closeMe();
                            });

                        }
                        else if (method == "update"){
                            layer.msg(room_update_success, {
                                icon: 1,
                                time: 2000
                            }, function () {
                                parent.location.reload();
                                closeMe();
                            });
                        }
                        return true;
                }
            }
        });
    }
}