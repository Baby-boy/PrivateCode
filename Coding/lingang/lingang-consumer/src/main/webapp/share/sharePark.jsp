<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	 <head>
        <meta charset="utf-8">
        <title>临港集团</title>
        <meta name="description" content="">
        <meta name="author" content="临港集团">
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
        <meta name="apple-mobile-web-app-title" content="临港集团">
        <link rel="stylesheet" href="css/lingang-allcss.css">
        <link rel="stylesheet" type="text/css" href="css/css/garden_article.css"/>
        <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js 无法工作在 以文件目录形式打开方式，例如 file:// -->
        <!--[if lt IE 9]> <script src="/js/lib/html5shiv.min.js"></script> <script src="/js/lib/respond.js"></script> <![endif]-->
        <!--end 引用/inc/home_css.html-->

        <!-- Favicons -->
        <link rel="shortcut icon" href="favicon.ico">
        <!-- IOS / Android 主屏图标 -->
        <link href="css/images/webappicon/apple-touch-icon.png" rel="apple-touch-icon"/>
        <link href="css/images/webappicon/apple-touch-icon-76x76.png" rel="apple-touch-icon" sizes="76x76"/>
        <link href="css/images/webappicon/apple-touch-icon-120x120.png" rel="apple-touch-icon" sizes="120x120"/>
        <link href="css/images/webappicon/apple-touch-icon-152x152.png" rel="apple-touch-icon" sizes="152x152"/>
        <link href="css/images/webappicon/apple-touch-icon-180x180.png" rel="apple-touch-icon" sizes="180x180"/>
        <link href="css/images/webappicon/icon-hires.png" rel="icon" sizes="192x192"/>
        <link href="css/images/webappicon/icon-normal.png" rel="icon" sizes="128x128"/>
        <!-- Tile icon for Win8 (144x144 + tile color) -->
        <!-- win 8 磁贴标题 -->
        <meta name="application-name" content="临港集团">
        <!-- win 8 磁贴颜色 -->
        <meta name="msapplication-TileColor" content="#ffffff">
        <!-- win 8 磁贴图标 -->
        <meta name="msapplication-TileImage" content="/images/webappicon/apple-touch-icon-120x120.png">
        <meta name="msapplication-tooltip" content="Tooltip">
        <meta http-equiv="Cache-Control" content="no-siteapp">
        <!--调试阶段禁止缓存,例如微信，QQ浏览器缓存-->
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <meta name="format-detection" content="telephone=no"/>
        <script src="js/lib/browser.js"></script>
    </head>
	<body>
		<header>
			<div class="gar_title">
				<span>产业园区详情</span>
			</div>
		</header>
		<!--  	<div class="banner_letter" style="background-image:url(css/img/Parkp-banner.png);">		-->	
		      <div class="banner_letter" style="background-image:url(${data.maxImgPath});">		
					<div class="b_center">
						<h4>${data.parkName}</h4>
						<ul>
						<c:forEach items="${data.labels}" var="dl">
							<li>
                            <a href="">${dl.labelName}</a>
                            </li>
							
							</c:forEach>
						</ul>
					</div>
				<div class="bar_border"></div>
			</div>			
		<section>
			<div class="article">
				<h4>园区简介</h4>
				<p>
					${data.parkContent}
				</p>
			</div>
			<div class="gar_video">
				<video src="${data.files[0].fileAddress}" poster="css/img/park-detail.jpg" controls="controls"></video>
			</div>
		</section>
		<footer>
			<div class="foot">
				<p>
					Copyright © 2007-2016 上海临港经济发展集团有限公司
				</p>
					<span>版权所有 </span> 
				
			</div>
		</footer>
	</body>
</html>
