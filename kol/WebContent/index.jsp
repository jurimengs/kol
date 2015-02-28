<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>人生百科</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">

<link href="${ctx }/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/main.css">
<link rel="stylesheet" href="${ctx }/css/mask.css">
<link rel="stylesheet" href="${ctx }/css/pad.css" media="only screen and (min-width : 768px) and (max-width : 1200px)">

<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.SuperSlide.2.1.1.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="js/html5shiv.min.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG.js"></script>
<script type="text/javascript">DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--修复IE6下PNG图片背景透明-->
</head>

<body>
	<header>
		<div id="logo" class="fl">
			<a href="#">
				人生<span>BAI</span>科
			</a>
		</div>
		<nav class="fr">
			<div class="btn-gp fr">
				<a href="#">
					登录
				</a>
				<a href="#">
					注册
				</a>
			</div>
			<ul class="fr">
				<li>
					<a href="#">
						吐槽吧
					</a>
				</li>
				<li>
					<a href="#">
						其他
					</a>
				</li>
				<li>
					<a href="#">
						事业
					</a>
				</li>
				<li>
					<a href="#">
						情感
					</a>
				</li>
				<li>
					<a href="#">
						生活
					</a>
				</li>
			</ul>
		</nav>
		<div class="clear">
		</div>
	</header>
	<div id="banner">
		<div id="slideBox" class="slideBox">
			<div class="bd">
				<ul>
					<li>
						<a href="#">
							<img src="images/banner1.jpg">
						</a>
					</li>
					<li>
						<a href="#">
							<img src="images/banner2.jpg">
						</a>
					</li>
					<li>
						<a href="#">
							<img src="images/banner3.jpg">
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="comwidth">
			<h1>
				感知生活
				<span>
					100%
				</span>
				了解您、
				<span>
					No.1
				</span>
				国内首页人生BAI科平台、
				<span>
					1,000,000
				</span>
				位的访问者即将来临
			</h1>
			<section class="first left">
				<article>
					<div class="zhuti-bar">
						<a href="#" class="zhuti">
							<img src="images/aimg1.jpg" alt="zhuti">
						</a>
						<p>
							<a href="javascript:void(0);" onclick="openComments();">
								评价
							</a>
						</p>
					</div>
					<ul class="pinglun">
						<li class="first">
							<a href="#" class="touxiang">
								<img src="images/30.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#" class="name">
									Jack
								</a>
								分享到
								<a href="#">
									网站模板
								</a>
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									0032032
								</a>
								看看
							</div>
							<p class="clear">
							</p>
						</li>
					</ul>
				</article>
			</section>
			<section>
				<article>
					<div class="zhuti-bar">
						<a href="#" class="zhuti">
							<img src="images/aimg2.jpg" alt="zhuti">
						</a>
						<p>
							<a href="#">
								jQuery选项卡形式图片瀑布流布局选项卡切换效果代码
							</a>
						</p>
					</div>
					<ul class="pinglun">
						<li class="first">
							<a href="#" class="touxiang">
								<img src="images/30.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#" class="name">
									Jack
								</a>
								分享到
								<a href="#">
									Jquery
								</a>
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									甜心
								</a>
								谢谢分享！
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									笑对人生
								</a>
								挺好的
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									女孩无忧
								</a>
								谢谢
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									万林
								</a>
								很棒
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									烟雨楼台
								</a>
								感谢分享非常好
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									死马破车
								</a>
								好
							</div>
							<p class="clear">
							</p>
						</li>
					</ul>
				</article>
			</section>
			<section class="left">
				<article>
					<div class="zhuti-bar">
						<a href="#" class="zhuti">
							<img src="images/aimg3.jpg" alt="zhuti">
						</a>
						<p>
							<a href="#">
								html5 modernizr图片瀑布流布局点击弹出图片滑动预览
							</a>
							<a href="#" class="fenlei">
								css3
							</a>
							<a href="#" class="fenlei">
								html5
							</a>
							<a href="#" class="fenlei">
								瀑布流
							</a>
						</p>
					</div>
					<ul class="pinglun">
						<li class="first">
							<a href="#" class="touxiang">
								<img src="images/30.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#" class="name">
									孙俊杰
								</a>
								分享到
								<a href="#">
								</a>
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									嘉盛
								</a>
								修改起来不是很方便
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									花儿对我笑
								</a>
								好
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									在路上
								</a>
								真不错，就是不兼容IE9一下的浏览器
							</div>
							<p class="clear">
							</p>
						</li>
					</ul>
				</article>
			</section>
			<section>
				<article>
					<div class="zhuti-bar">
						<a href="#" class="zhuti">
							<img src="images/aimg4.jpg" alt="zhuti">
						</a>
						<p>
							<a href="#">
								html5 CSS3模拟视差图片跳动瀑布流布局效果
							</a>
							<a href="#" class="fenlei">
								瀑布流式布局
							</a>
							<a href="#" class="fenlei">
								css3
							</a>
							<a href="#" class="fenlei">
								html5
							</a>
							<a href="#" class="fenlei">
								视差效果
							</a>
						</p>
					</div>
					<ul class="pinglun">
						<li class="first">
							<a href="#" class="touxiang">
								<img src="images/30.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#" class="name">
									黑暗中的舞者
								</a>
								分享到
								<a href="#">
									图片代码
								</a>
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									苏醒
								</a>
								好用
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									宅男福利
								</a>
								不错呀感谢
							</div>
							<p class="clear">
							</p>
						</li>
						<li>
							<a href="#" class="touxiang">
								<img src="images/29.jpg" alt="touxiang">
							</a>
							<div>
								<a href="#">
									幸福来敲门
								</a>
								不错，好用
							</div>
							<p class="clear">
							</p>
						</li>
					</ul>
				</article>
			</section>
			<div class="clear">
			</div>
		</div>
	</div>
	<footer>
		<div class="comwidth">
			<p class="fl">
				人生BAI科 © 2015 www.rsbk.com 版权所有
				<span>
					|
				</span>
				<a href="#">
					联系我们
				</a>
			</p>
			<ul class="foot-nav fr">
				<li>
					<a href="#">
						排行榜
					</a>
				</li>
				<li>
					<a href="#">
						标签搜索
					</a>
					<span>
						|
					</span>
				</li>
				<li>
					<a href="#">
						联系我们
					</a>
					<span>
						|
					</span>
				</li>
				<li>
					<a href="#">
						积分获取
					</a>
					<span>
						|
					</span>
				</li>
				<li>
					<a href="#">
						关于我们
					</a>
					<span>
						|
					</span>
				</li>
			</ul>
			<div class="clear">
			</div>
		</div>
	</footer>
</body>


<script type="text/javascript">
	//alert("${ctx}");
	//$d("maskDiv").commentsModule();
	function openComments() {
		$d("maskDiv").dialogComments();
	}

	function openTestimonials() {
		var submitBtnId = $d("maskDiv").dialogTestimonials();
		$d(submitBtnId).onclick(function() {
			alert("sfsdf");
		});
	}

	$("#userLogin").click(function() {
		$("#realLoginName").val($("#username").val());
		$("#realLoginPwd").val($("#loginpwd").val());
		$("#loginForm").prop("action", "${ctx}/user/userLogin.do");

		var ids = [ 'username', 'loginpwd' ];
		if (notNullArr(ids)) {
			$("#loginForm").submit();
		}
	});
	// 
	jQuery(".slideBox").slide({
		mainCell : ".bd ul",
		autoPlay : true
	});
</script>
</html>