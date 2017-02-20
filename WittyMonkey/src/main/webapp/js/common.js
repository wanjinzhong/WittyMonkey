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
function reload(){
    window.location.reload();
}
/**
 * 日期格式化
 */
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