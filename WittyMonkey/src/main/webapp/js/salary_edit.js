/**
 * Created by neilw on 2017/4/20.
 */
var layer;
layui.use('layer',function () {
    layui.layer;
});
function toBack() {
    window.location.href = "toSalaryChangeRecord.do?id=" + $("#id").val();
}