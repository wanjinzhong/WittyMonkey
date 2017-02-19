/** index.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */
layui.config({
    base: 'js/'
}).use(['element', 'layer', 'navbar', 'tab'], function () {
    var element = layui.element(),
        $ = layui.jquery,
        layer = layui.layer,
        navbar = layui.navbar(),
        tab = layui.tab({
            elem: '.admin-nav-card' //设置选项卡容器

        });
    //iframe自适应
    $(window).on('resize', function () {
        var $content = $('.admin-nav-card .layui-tab-content');
        $content.height($(this).height() - 147);
        $content.find('iframe').each(function () {
            $(this).height($content.height());
        });
    }).resize();

    //设置navbar
    navbar.set({
        spreadOne: false,
        elem: '#admin-navbar-side',
        cached: true,
        url: 'getMenu.do'
    });
    //渲染navbar
    navbar.render();
    //监听点击事件
    navbar.on('click(side)', function (data) {
        tab.tabAdd(data.field);
    });

});
function sideBtnClick() {
    var sideWidth = $('#admin-side').width();
    if (sideWidth === 200) {
        $('#admin-body').animate({
            left: '0'
        }); //admin-footer
        $('#admin-footer').animate({
            left: '0'
        });
        $('#admin-side').animate({
            width: '0'
        });
    } else {
        $('#admin-body').animate({
            left: '200px'
        });
        $('#admin-footer').animate({
            left: '200px'
        });
        $('#admin-side').animate({
            width: '200px'
        });
    }
}