/**
 * Created by neilw on 2017/2/20.
 */
var layer;
layui.use('layer',function () {
    layer = layui.layer;
});


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

function save() {
    if (validateFloorNo("add", $("#floorNo"))) {
        var load = layer.load();
        $.ajax({
            type: "GET",
            url: "saveFloor.do",
            data: $("#add_form").serialize(),
            dataType: "json",
            success: function (data) {
                layer.close(load);
                var funResult = eval("(" + data + ")");
                switch (funResult.status) {
                    case 400:
                        layer.tips(floor_validate_no_exist, $("#floorNo"), {tips: 2});
                        break;
                    case 401:
                        layer.tips(floor_validate_no_wrong, $("#floorNo"), {tips: 2});
                        break;
                    case 410:
                        layer.tips(messageOfValidateLength($("note"), 1024), $("#note"), {tips: 2});
                        break;
                    case 500:
                        layer.msg(error_500, {
                            icon: 2, time: 2000
                        }, function () {
                            parent.location.reload();
                            closeMe();
                        });
                        break;
                    case 200 :
                        layer.msg(floor_manage_add_success, {
                        icon: 1,
                        time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                        break;
                }
            }
        });
    }
}