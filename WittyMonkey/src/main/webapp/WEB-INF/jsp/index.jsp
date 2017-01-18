<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%@ include file="common/taglib.jsp"%>
<%@ include file="common/i118n.jsp"%>
<%@ include file="common/js&css.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="name" /></title>
<link href="lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet"
	type="text/css" />
</head>
<style>
.navbar-wrapper {
	height: 80px;
}

#logo {
	width: 50px;
	height: 50px;
}
</style>
<body>
	<div class="contextMenu" id="myMenu1">
		<ul>
			<li id="open">Open</li>
			<li id="email">email</li>
			<li id="save">save</li>
			<li id="delete">delete</li>
		</ul>
	</div>
	<div class="contextMenu" id="logo">
		<ul>
			<li id="out">退出</li>
		</ul>
	</div>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs" href="#"><img
					class="logo" src="pic/logo.gif" /></a> <a aria-hidden="false"
					class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
				<nav class="nav navbar-nav">
					<ul class="cl">
						<li class="dropDown dropDown_hover"><a href="javascript:;"
							class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i
								class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;"
									onclick="article_add('添加资讯','article-add.html')"><i
										class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
								<li><a href="javascript:;"
									onclick="picture_add('添加资讯','picture-add.html')"><i
										class="Hui-iconfont">&#xe613;</i> 图片</a></li>
								<li><a href="javascript:;"
									onclick="product_add('添加资讯','product-add.html')"><i
										class="Hui-iconfont">&#xe620;</i> 产品</a></li>
								<li><a href="javascript:;"
									onclick="member_add('添加用户','member-add.html','','510')"><i
										class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
							</ul></li>
					</ul>
				</nav>
				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li>超级管理员</li>
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="#">个人信息</a></li>
								<li><a href="#">切换账户</a></li>
								<li><a href="#">退出</a></li>
							</ul></li>
						<li id="Hui-msg"><a href="#" title="消息"><span
								class="badge badge-danger">1</span><i class="Hui-iconfont"
								style="font-size: 18px">&#xe68a;</i></a></li>
						<li id="Hui-skin" class="dropDown right dropDown_hover"><a
							href="javascript:;" class="dropDown_A" title="换肤"><i
								class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default"
									title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<input runat="server" id="divScrollValue" type="hidden" value="" />
		<div class="menu_dropdown bk_2">
			<dl id="menu-floor">
				<dt>
					<i class="Hui-iconfont">&#xe616;</i> <fmt:message key="index.menu.floor"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.floor.summary"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.floor.summary"/></a></li>
							<li><a data-href="#" data-title="<fmt:message key="index.menu.floor.add"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.floor.add"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-room">
				<dt>
					<i class="Hui-iconfont">&#xe613;</i> <fmt:message key="index.menu.room"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.room.query"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.room.query"/></a></li>
							<li><a data-href="#" data-title="<fmt:message key="index.menu.room.add"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.room.add"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-checkin">
				<dt>
					<i class="Hui-iconfont">&#xe620;</i> <fmt:message key="index.menu.checkin"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.checkin.checkin"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.checkin.checkin"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.checkin.change_room"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.checkin.change_room"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.checkin.checkout"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.checkin.checkout"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-materiel">
				<dt>
					<i class="Hui-iconfont">&#xe622;</i> <fmt:message key="index.menu.materiel"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#"
							data-title="<fmt:message key="index.menu.meteriel.query"/>" href="javascript:;"><fmt:message key="index.menu.meteriel.query"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.meteriel.add"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.meteriel.add"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-inventory">
				<dt>
					<i class="Hui-iconfont">&#xe60d;</i> <fmt:message key="index.menu.inventory"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.inventory.in"/>"
							href="javascript:;"><fmt:message key="index.menu.inventory.in"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.inventory.out"/>"
							href="javascript:;"><fmt:message key="index.menu.inventory.out"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-staff">
				<dt>
					<i class="Hui-iconfont">&#xe62d;</i> <fmt:message key="index.menu.staff"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.staff.query"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.staff.query"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.staff.add"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.staff.add"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-leave">
				<dt>
					<i class="Hui-iconfont">&#xe61a;</i> <fmt:message key="index.menu.leave"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.leave.record"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.leave.record"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.leave.add"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.leave.add"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-finance">
				<dt>
					<i class="Hui-iconfont">&#xe62e;</i> <fmt:message key="index.menu.finance"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.finance.add"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.finance.add"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.finance.reimburse"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.finance.reimburse"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.finance.salary_change"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.finance.salary_change"/></a></li>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.finance.salary"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.finance.salary"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-report">
				<dt>
					<i class="Hui-iconfont">&#xe61a;</i> <fmt:message key="index.menu.report"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.report"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.report"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-notify">
				<dt>
					<i class="Hui-iconfont">&#xe61a;</i> <fmt:message key="index.menu.notify"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.notify"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.notify"/></a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-settting">
				<dt>
					<i class="Hui-iconfont">&#xe61a;</i> <fmt:message key="index.menu.settting"/><i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="#" data-title="<fmt:message key="index.menu.settting"/>"
							href="javascript:void(0)"><fmt:message key="index.menu.settting"/></a></li>
					</ul>
				</dd>
			</dl>
		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
			</div>
		</div>
	</section>



	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.1/layer.js"></script>
	<script type="text/javascript"
		src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".logo").contextMenu('logo', {
				bindings : {
					"out" : function(t) {
						window.location.href = "toLogin.do";
					}
				}
			});
			$(".Hui-tabNav-wp").contextMenu('myMenu1', {
				bindings : {
					'open' : function(t) {
						alert('Trigger was ' + t.id + '\nAction was Open');
					},
					'email' : function(t) {
						alert('Trigger was ' + t.id + '\nAction was Email');
					},
					'save' : function(t) {
						alert('Trigger was ' + t.id + '\nAction was Save');
					},
					'delete' : function(t) {
						alert('Trigger was ' + t.id + '\nAction was Delete')
					}
				}
			});
		});
		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>

	<!--此乃百度统计代码，请自行删除-->
	<script type="text/javascript">
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s)
		})();
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<!--/此乃百度统计代码，请自行删除-->
</body>
</html>