<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/common/common.jsp"%>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

    
<meta name="apple-mobile-web-app-capable" content="yes" />    
<meta name="format-detection" content="telephone=no" />  
<title>我的货架</title>
<link href="/www/css/shelf.css" rel="stylesheet" type="text/css">
<script type="text/javascript" charset="utf-8" src="/www/cordova.js"></script>
<script type="text/javascript" charset="utf-8" src="/www/index.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/common.js"></script>

<script type="text/javascript" charset="utf-8">
    //拍照成功后回调
    function onLoadImageSuccess(imageURI) {
    	try{

        	// 调试慎用.以防字符过长手机端卡死
            //navigator.notification.alert(imageURI, null, "警告");
            //这里的图片经过了base64编码
            var src = "data:image/jpeg;base64," + imageURI;
            $("#getImage").attr("src", src);
            $("#getImage").show();
    	}catch(e) {
    		$("#errmsg").val(e);
    	}
    }
    //所有获取图片失败都回调此函数
    function onLoadImageFail(message) {
    	try{

            navigator.notification.alert("拍照失败，原因：" + message, null, "警告");
    	}catch(e) {
    		$("#errmsg").val(e);
    	}
    }
    function loadImageLocal() {
    	try{

            //获取本地图片并显示在屏幕
            navigator.camera.getPicture(onLoadImageLocalSuccess, onLoadImageFail, {
                destinationType: Camera.DestinationType.FILE_URI,
                sourceType: Camera.PictureSourceType.PHOTOLIBRARY
            });
    	}catch(e) {
    		$("#errmsg").val(e);
    	}
    }
    //本地图片选择成功后回调此函数
    function onLoadImageLocalSuccess(imageURI) {
    	try{

            $("#getImageLocal").attr("src", imageURI);
            $("#getImageLocal").show();
    	}catch(e) {
    		$("#errmsg").val(e);
    	}
    }
    function loadImageUpload() {
    	try{

            //拍照上传并显示在屏幕
            navigator.camera.getPicture(onLoadImageUploadSuccess, onLoadImageFail, {
                destinationType: Camera.DestinationType.FILE_URI,
                quality: 50,
                sourceType: 1,
                allowEdit: true, // 剪裁框
                targetWidth: 100, 
                targetHeight: 100
            });
    	}catch(e) {
    		$("#errmsg").val(e);
    	}
    }
    //图片拍照成功后回调此函数
    function onLoadImageUploadSuccess(imageURI) {
    	try{

            //此处执行文件上传的操作，上传成功后执行下面代码
            var options = new FileUploadOptions(); //文件参数选项
            options.fileKey = "file";//向服务端传递的file参数的parameter name
            options.fileName = imageURI.substr(imageURI.lastIndexOf('/') + 1);//文件名
            options.mimeType = "image/jpeg";//文件格式，默认为image/jpeg
            var ft = new FileTransfer();//文件上传类
            ft.onprogress = function (progressEvt) {//显示上传进度条
                if (progressEvt.lengthComputable) {
                    navigator.notification.progressValue(Math.round(( progressEvt.loaded / progressEvt.total ) * 100));
                }
            }
            var src = imageURI;
            // TODO 这个进度提示应该弄成弹出层
            navigator.notification.progressStart("提醒", "当前上传进度");
            ft.upload(imageURI, encodeURI('http://192.168.22.171:7080/upload.do'), function () {
            	try {
            		var startLocation = imageURI.lastIndexOf("/")+1;
                	var fileName = imageURI.substring(startLocation, imageURI.length);
                    navigator.notification.progressStop();//停止进度条
                    var serverFileName = "http://192.168.22.171:7080/files/"+ getYYYYMMDD()+"/"+fileName;
                    $("#getImageUpload").attr("src", serverFileName);
                    $("#imageUploadUrl").val(serverFileName);
                    $("#getImageUpload").show();
                    navigator.notification.alert("文件上传成功！", null, "提醒");
            	} catch(e) {
            		alert(e);
            	}
            }, null, options);
    	}catch(e) {
    		$("#errmsg").val(e);
    	}
    }
    
    function openUrl (url) {
    	window.location.href = url;
    }
</script>
    
</head>

<body>
<ul>
	<li class="head clear">
        <div class="top_module_left flo_left"><a href="#">返回</a></div>
        <div class="top_module_f"><a href="javascript:void(0);" onclick="">货架管理</a></div>
        <div class="top_module_right"><a href="#">删除</a></div>
    </li>
    <li class="main_mod clear">
    	<!--mod1-->
    	<c:forEach var="goods" items="${goodsArr }" varStatus="ci">
	    	<ul class="mod_public  flo_left">
	        	<li class="marg_rig_6" onclick="openUrl('/goods/edit.do?goodsId=${goods.id}')">
	            	<div class="pos_rel flo_left" >
	                	<div class="pos_obso inp_squre_sel"></div>
	                	<div class="squre_avter"><img src="${goods.picPath}" width="54" height="54"></div>
	                </div>
	                <div class="pos_rel flo_right descrip_rig">
	                	<h5>汽车美容务服务…</h5>
	                    <font><span class="fen">￥</span>${goods.goodsPrice}<span class="fen">.00</span></font>
	                </div>
	                <div class="amount">
	                	<div class="flo_left"><a href="#"><img src="../images/plus_duck.png" width="15" height="15" ></a></div>
	                    <div class="amo_f">${goods.goodsCounts}</div>
	                    <div class="amo_right"><a href="#"><img src="../images/minus_gray.png" width="15" height="15"></a></div>
	                </div>
	            </li>
	        </ul><!--mod1 over-->
        </c:forEach>
    </li>
    <li class="footer clear">
    	<div class="flo_left" style="margin-left:10px;"><input name="" type="radio" value="全选">全选 </div><div class="flo_right" style="margin-right:10px;"><input name="" class="btn_change_val" type="text" value="一键改价" onfocus="if (value =='一键改价'){value =''}" onblur="if (value ==''){value='一键改价'}"><strong style="color:#F30;">￥299.<span class="fen">00</span></strong><input name="" class="btn_collect"type="button" value="收款(2)"></div>
    </li>
</ul>

</body>
</html>
