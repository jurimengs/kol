/*
 * COM check
 *
 * DESCRIPTION
 *   check browser only ie.version > 6.0 
 *   check com use activex
 *
 * since: checkcom.js,v 1.0 2013/01/07 16:05:08
 * 
 *        checkcom.js,v 2.0 2013/05/30 13:44:08 include before use com check condition and add it in page by different os and browser
 *             
 * 
 */

var ENV = function() {
	
	//操作系统  
	this.OS = {
	  				T:'Win',
	 				V:'8'
			  };
	
	//浏览器
	this.BROWSER = {
					T:'IE',
					V:'10.0'
				    };
	
	//组件
	this.COMPONENT= {};
	
	//是否PC, 默认PC
	this.isPc = true;
	
};

var ActiveCheckPlan = {
	AutoReloadCom:"A", // 无刷新重加载方案
	AutoRefresh:"B", // 自动刷新实现
	RestartBrowser:"C" // 重启浏览器
};

var ActiveCheckTips = {
	NoneRestartBrowser:"检测到新版密码安全控件,请点击<a href='/com/win/passput_setup.exe?time="+new Date().getTime()+"' onclick='downloadActive();'>下载</a>; <br />安装完成后,页面将自动加载控件;如页面长时间未刷新,请点击<a href='javascript:void();' onclick='refreshPage();'>刷新</a>",
	RestartBrowser:"检测到新版密码安全控件,请点击<a href='/com/win/passput_setup.exe?time="+new Date().getTime()+"' onclick='downloadActive();'>下载</a>; 安装完成后,请重启浏览器",
	MacTips:"检测到新版密码安全控件,请点击<a href='/com/mac/PassPut_setup.dmg?time="+new Date().getTime()+"' onclick='downloadActive();'>下载</a>; 您使用的浏览器不支持自动加载控件,请重启浏览器"
};

var CardActiveCheckTips = {
	NoneRestartBrowser:"<span style='color:#ff8000;font-size:14px;'>当前未检测到读卡器安全控件, 如您未下载安装, 请<a href='#' id='cardActiveDown'> 下载 </a>安装<br />如您已下载安装,请点击<a href='javascript:void();' onclick='refreshPage();'>刷新</a></span>",
	RestartBrowser:"<span style='color:#ff8000;font-size:14px;'>当前未检测到读卡器安全控件, 如您未下载安装, 请<a href='#' id='cardActiveDown'> 下载 </a>安装<br />如您已下载安装,请重启浏览器</span>"
};

var ReDownLoadCardActiveTips = {
	NoneRestartBrowser:"<span style='color:#ff8000;font-size:14px;'>读卡器功能安全控件有新版本, 请 <a href='#' id='cardActiveDown'>下载</a>新控件<br />安装完成后,请点击<a href='javascript:void();' onclick='refreshPage();'>刷新</a></span>",
	RestartBrowser:"<span style='color:#ff8000;font-size:14px;'>读卡器功能安全控件有新版本, 请 <a href='#' id='cardActiveDown'>下载</a>新控件<br />安装完成后,请重启浏览器</span>"
};

ENV.prototype = {
	_setPc:function(){
		var sUserAgent= navigator.userAgent.toLowerCase();
		var bIsIpad= sUserAgent.match(/ipad/i) == "ipad";
		var bIsIphoneOs= sUserAgent.match(/iphone os/i) == "iphone os";
		var bIsMidp= sUserAgent.match(/midp/i) == "midp";
		var bIsUc7= sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
		var bIsUc= sUserAgent.match(/ucweb/i) == "ucweb";
		var bIsAndroid= sUserAgent.match(/android/i) == "android";
		var bIsCE= sUserAgent.match(/windows ce/i) == "windows ce";
		var bIsWM= sUserAgent.match(/windows mobile/i) == "windows mobile";
		if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
			this.isPc = false;
		} else {
			this.isPc = true;
		} 
	},
	_setOS:function(){//获取操作系统
	   var ua = navigator.userAgent.toLowerCase();
	   var pf  = navigator.platform.toLowerCase();

	   var isWin = (pf == "win32") || (pf == "windows");
	   var isMac = (pf == "mac68k")|| (pf == "macppc") || (pf == "macintosh") || (pf == "macintel");          
	   if(isWin){
		   this.OS.T = 'Win';	   
		   if(ua.indexOf("windows nt 5.0") != -1 || ua.indexOf("windows 2000") != -1){
			    this.OS.V = "2000";
		   }else if(ua.indexOf("windows nt 5.1") != -1 || ua.indexOf("windows xp") != -1){
			    this.OS.V = "XP";
		   }else if(ua.indexOf("windows nt 5.2") != -1 || ua.indexOf("windows 2003") != -1){
			    this.OS.V = "2003";
		   }else if(ua.indexOf("windows nt 6.0") != -1 || ua.indexOf("windows vista") != -1){
			    this.OS.V = "Vista";
		   }else if(ua.indexOf("windows nt 6.1") != -1 || ua.indexOf("windows 7") != -1){
			    this.OS.V = "7";
		   }else if(ua.indexOf("windows nt 6.2") != -1 || ua.indexOf("windows 8") != -1){
			    this.OS.V = "8";
		   }else{
			    this.OS.V = "8";
		   }
		   
	   }else{
		 if (isMac) {
		    this.OS.T = 'Mac';
		   if(ua.indexOf('mac') != -1){
			  this.OS.V = "";
    	   }
	     }else{
	       if(ua.indexOf('x11') != -1){
	    	  this.OS.T = 'Unix';
	    	  this.OS.V = ""; 
	       }else if(ua.indexOf('linux') != -1){
	    	  this.OS.T = 'Linux';
	    	  this.OS.V = "";
	       } 
	     }
	   }  
	},
	
	_setBrowser:function(){//获取浏览器
		var ua = navigator.userAgent.toLowerCase();
 	  	// 默认提示
 	  	this.BROWSER.checkTips = ActiveCheckTips.NoneRestartBrowser;
 	  	this.BROWSER.readCardCheckTips = CardActiveCheckTips.NoneRestartBrowser;
 	  	this.BROWSER.reDownLoadCardActiveTips = ReDownLoadCardActiveTips.NoneRestartBrowser;
 	  	// 默认执行无刷新 重加载
 	  	this.BROWSER.autoCheckActive = function(cb){
 	  		// TODO alert("执行无刷新 重加载");
			window.setTimeout("env._checkComponent("+cb+")",1000*5*1);	
	  	};

		if (window.ActiveXObject){
    	    var ieMatch = ua.match(/msie ([\d.]+)/);
    	    if(ieMatch && ieMatch.length >1){
         	  	this.BROWSER.T = "IE"; 
         	  	this.BROWSER.V = ieMatch[1];
    	    }
        }
		
		if (document.getBoxObjectFor || true){
    	 	var firefoxMatch = ua.match(/firefox\/([\d.]+)/);
    	 	if(firefoxMatch  && firefoxMatch.length > 1){
    		  	this.BROWSER.T = "FireFox"; 
         	  	this.BROWSER.V = firefoxMatch[1];
         	  	// 采用自动刷新方案
         	  	this.BROWSER.checkPlan = ActiveCheckPlan.AutoRefresh;
         	  	this.BROWSER.autoCheckActive = function(cb){
         	  		// TODO alert("执行自动刷新加载");
     				window.setTimeout("history.go(0)",1000*5*1);	
         	  	};
    	 	}
    	}
		
		if (window.MessageEvent && !document.getBoxObjectFor){
    		var chromeMatch = ua.match(/chrome\/([\d.]+)/);
    		if(chromeMatch && chromeMatch.length > 1){
    			var baiduMatch = ua.match(/bidubrowser\/([\d.]+)/);
        		if(!!baiduMatch){
    			  	this.BROWSER.T = "BaiDu"; 
    	     	  	this.BROWSER.V = ua.match(/bidubrowser\/([\d.]+)/)[1];
    	     	  	// 采用自动刷新方案
    	     	  	this.BROWSER.checkPlan = ActiveCheckPlan.AutoRefresh;
    	     	  	this.BROWSER.autoCheckActive = function(cb){
    	     	  		// TODO alert("执行自动刷新加载");
    	 				window.setTimeout("history.go(0)",1000*5*1);	
    	     	  	};
    			} else {
        		 	this.BROWSER.T = "Chrome"; 
             	  	this.BROWSER.V = chromeMatch[1];
             	  	// 采用无刷新重加载方案
    			}
        		// chrome 内核,读卡器功能控件需要重启
         	  	this.BROWSER.readCardCheckTips = CardActiveCheckTips.RestartBrowser;
         	  	this.BROWSER.reDownLoadCardActiveTips = ReDownLoadCardActiveTips.RestartBrowser;
    		}
    	}
		
		if (window.opera){
    		var operaMatch = ua.match(/opera.([\d.]+)/);
    		if(operaMatch && operaMatch.length > 1){
    			this.BROWSER.T = "Opera"; 
         	  	this.BROWSER.V = operaMatch[1];
         	  	// 采用无刷新重加载方案
    		}
    		// chrome 内核,读卡器功能控件需要重启
     	  	this.BROWSER.readCardCheckTips = CardActiveCheckTips.RestartBrowser;
     	  	this.BROWSER.reDownLoadCardActiveTips = ReDownLoadCardActiveTips.RestartBrowser;
    	}
		
		if (window.openDatabase){
    		var safariMatch = ua.match(/version\/([\d.]+)/);
    		if(safariMatch && safariMatch.length > 1){
    		    this.BROWSER.T = "Safari"; 
         	  	this.BROWSER.V = safariMatch[1];
         	  	this.BROWSER.checkTips = ActiveCheckTips.RestartBrowser;
         	  	this.BROWSER.autoCheckActive = function(cb){
         	  		// TODO alert("无执行");
         	  	};
    		} 	
    	}
	},
	
    _getO:function(){
		return this.OS;
	},
		
	_getB:function(){
		return this.BROWSER;
	},
	
	_getC:function(){
		return this.COMPONENT;
	},
			
    _init:function(){//环境识别
		
		//checkBrowser();
    	this._setPc();
		this._setOS();
		this._setBrowser();
		//alert("ENV.OS.T:"+this.OS.T+",ENV.OS.V:"+this.OS.V+",ENV.BROWSER.T:"+this.BROWSER.T+",ENV.BROWSER.V:"+this.BROWSER.V);
		this._initComponent();
	},
	
	  _initComponent:function(){

		if(!this.isPc){
			// 如果不是PC
		    var title = "暂不支持您所使用的设备访问本站, 请使用电脑访问";
		    var tableHtml = "";
			var btna = {id:"stepReturn", name:"", value:"返回", className:"btn"};
			var btns = [btna];
			createMessageBlock("firfox_message_div");
			createCommonTips(tableHtml, btns, title);
			var btnDom = document.getElementById("stepReturn");
			try{
				btnDom.attachEvent("onclick", function(){
					history.back();
				});
			}catch(e){
				btnDom.addEventListener("onclick", function(){
					history.back();
				});
			}
			
			return;
		}
		//加载组件
	    if(this.OS.T  == 'Win'){
		   this.COMPONENT = new Win(this);
		   this.COMPONENT._initCom();
		}else if(this.OS.T  == 'Mac'){
		   this.COMPONENT = new Win(this);
		   this.COMPONENT._initMacCom();
		}else if(this.OS.T  == 'Unix'){
			
			
		}else if(this.OS.T  == 'Linux'){
				
		}
	},
		
	_checkComponent:function(cb){//检测组件
	  	if(this.OS.T  == 'Win'){
		    this.COMPONENT._checkCom(cb);
		}else if(this.OS.T  == 'Mac'){
			this.COMPONENT._checkMacCom(cb);
		}else if(this.OS.T  == 'Unix'){
			
			
		}else if(this.OS.T  == 'Linux'){
				
		}
	}
	
	
};

var env = new ENV();


//Windows Extends
var Win = function(p){
	//环境
	this.parent = p;
	//控件
	this.com = {};
	//控件的层标识
	this.com.DIV_ID = 'com_div';
	//控件Id标识
	this.com.ID = 'com';
	//控件模式: test(测试模式)/ online(生产模式)
	this.com.MODE = "online";
	//读卡器控件
	this.readCardCom = {};
	if(PSA._getWebRoot().indexOf("multipay.sandpay.com.cn") == -1){
		this.com.MODE = "test";
	}
	if(this.parent.BROWSER.T == 'IE'){
		//控件注册号
		this.com.CLASSID = 'clsid:6A4317E7-4767-4D1D-AE23-B5E8FC03046F';
		//控件内部版本
		this.com.INNERVERSION = 'Version=';
		//控件目录
		this.com.PATH = '/com/win/';
	    //控件宽度
	    this.com.WIDTH = '195';
	    //控件高度
	    this.com.HEIGHT = '95';
	    
	    this.com.INNERVERSION += '4,1,0,0';
	 	this.com.VERSION = '4100';
    	this.com.PATH += "wnt63/netpay.CAB";
    	
	}else{
		//控件目录
		this.com.PATH = '/com/win/passput_setup.exe';
		//控件程序版本
	 	this.com.VERSION = '5.1.2';
	 	//添加了读卡器控件
	 	this.readCardCom.VERSION = '1.0.0';
	}
		
};


//Mac Extends
var Mac = function(p){
	//环境
	this.parent = p;
	//控件
	this.com = {};
	//控件的层标识
	this.com.DIV_ID = 'com_div';
	//控件Id标识
	this.com.ID = 'com';
	//控件模式: test(测试模式)/ online(生产模式)
	this.com.MODE = "online";
	if(PSA._getWebRoot().indexOf("multipay.sandpay.com.cn") == -1){
		this.com.MODE = "test";
	}
	//控件目录
	this.com.PATH = '/com/win/passput_setup.exe';
	//控件程序版本
 	this.com.VERSION = '5.1.2';
};


function getIframeWindow(obj) {  
	return obj.contentWindow || obj.contentDocument.parentWindow;
}

/**
 * env这个变量是在页面加载checkcom.js的时候初始化的
 * @returns
 */
function isIe(){
	return env._getB().T == "IE";
}

function isFF(){
	return env._getB().T == "FireFox";
}

Win.prototype._initCom = function(){
	var obj=null;
	if(this.parent.BROWSER.T == 'IE'){
		var html ="<object id='"+this.com.ID+"' CLASSID='"+this.com.CLASSID+"' CODEBASE='"+this.com.PATH+"#"+this.com.INNERVERSION+"'  width='"+this.com.WIDTH+"' height='"+this.com.HEIGHT+"'></object>";
		if(this.parent.BROWSER.V == "8.0" || this.parent.BROWSER.V == "7.0"){
			// 要同时兼容ie 8, 及其iframe内嵌页面加载控件, 只能用这种写法
		    obj = document.createElement(html);
		} else {
			// 这种写法兼容于各种IE版本, 但是不兼容于ie 8的iframe内嵌页面加载控件
			var tempDiv = document.createElement("div"); 
			tempDiv.innerHTML = html;
			obj = tempDiv.childNodes[0];
			tempDiv = null;
		}
		document.getElementById(this.com.DIV_ID).appendChild(obj);
	} else{
		var html = '<object id="com" type="application/x-passput" width="0" height="0"></object>';
		var tempDiv = document.createElement("div"); 
		tempDiv.innerHTML = html;
		obj = tempDiv.childNodes[0];
		document.body.appendChild(obj);
		// 再加载一个控件,用于密码
		var html = '<object id="linkcr5h" type="application/x-linkcr5h" width="0" height="0"></object>';
		var tempDiv = document.createElement("div"); 
		tempDiv.innerHTML = html;
		obj = tempDiv.childNodes[0];
		document.body.appendChild(obj);
	}
};


Win.prototype._initMacCom = function(){
	var obj=null;
	var html = '<object id="com" type="application/x-passput" width="0" height="0"></object>';
	var tempDiv = document.createElement("div"); 
	tempDiv.innerHTML = html;
	obj = tempDiv.childNodes[0];
	document.body.appendChild(obj);
};

Win.prototype._checkCom = function(cb){
	var c = false;
	var com = document.getElementById(this.com.ID);
	if(this.parent.BROWSER.T == 'IE'){
	   if(parseInt(this.parent.BROWSER.V,10) < parseInt('6.0',10)){
		   createMessageBlock();
		   createCloseTips();
		   return false;
	   }
	   
	   var stat = '2';
	   try{
			if(typeof com == 'object'){
			  var version = com.GetVersion();
			  if(parseInt(this.com.VERSION, 10) > parseInt(version, 10)){
				   stat = '1';
			  }
			}else{
				stat = '0';
			}
		}catch(e){
			    stat = '0';
		}finally{
			try{
			  delete com;
			  com = null;
	  	    }catch(e){}
		}
		
		if(stat == '2'){
			c = true;
		}else{
			c = false;
		}

	    //检测成功
		if(c){	
		    if(cb && typeof cb == 'function') {
		    	cb();
		    }
			return true;
		}else{
			 document.getElementById('message_div').style.display = "block";
		     document.getElementById('message_loading_tip').innerHTML ="检测到新控件,正在为您下载,请稍候...";
			 window.setTimeout("env._checkComponent("+cb+")",1000*5*1);	
		}
	}else if(this.parent.BROWSER.T == 'FireFox'){
		newActiveCheck(com, this.com, cb);
	}else{
		newActiveCheck(com, this.com, cb);
	}
	
	
	
};

Win.prototype._checkMacCom = function(cb){
	var com = document.getElementById(this.com.ID);
	var msg = ActiveCheckTips.MacTips;
	newActiveCheck(com, this.com, cb, msg);
};

// TODO 
Mac.prototype._checkMacCom = function(cb){
	var com = document.getElementById(this.com.ID);
	newActiveCheck(com, this.com.version, cb, msg);
};

/**
 * 新版本控件检测,初始化函数
 * @param com 页面控件对象
 * @param comObj 根据将要加载的控件参数, 封装的对象
 * @param cb  检测控件后的函数.一般为控件初始化函数
 */
function newActiveCheck(com, comObj, cb, msg){
	if(!!! msg){
		msg = env._getB().checkTips;
	}
	// IE版本也有一个控件层, 在每个页面打开它的时候显示
	var oldBlock = document.getElementById('message_div');
	if(oldBlock){
		oldBlock.style.display = "none";
	}
	// 是否显示下载提示
	var showDownTip = false;
	if(!!! com.version){
		// 当检测不到控件版本的时候, 提示用户下载
		createMessageBlock();
		document.getElementById('firfox_message_body').innerHTML = msg;
		showDownTip = true;
	} else {
		// 如果检测到了, 先判断版本
		if(toInt(com.version) < toInt(comObj.VERSION)){
			// 当检测有新版本的时候
			createMessageBlock();
			document.getElementById('firfox_message_body').innerHTML = msg;
			showDownTip = true;
		}
	}
	
	if(showDownTip){
		// 提示用户下载模式, 并且执行定时检测新版本， 
		// 在浏览器检测的时候， 已定好检测方案，并实现了autoCheckActive函数， 这里直接调用即可
		env._getB().autoCheckActive(cb);
	} else {
		// 浏览器active插件模式，执行回调函数, 进行初始化操作
		// 执行前, 先设置模式
		document.getElementById(comObj.ID).SetMode(comObj.MODE);
		cb();
		// 关闭提示层
		document.getElementById("firfox_message_div").style.display = "none";
	}
}

//=====代替jquery==============================================================

var SandQuery = function(id){
    if ( window == this ){
		return new SandQuery(id);
	}
	 
	var dom;
	if(typeof id == "string"){
		dom = document.getElementById(id);
	} else {
		dom = id;
	}
	if(dom == null){
		alert("获取document失败: " + id);
	}
	try{
		if(dom.isSandObj){
			return dom;
		}
	} catch(e){
		dom.isSandObj = false;
	}
    return this.setObject(dom); 
};


SandQuery.prototype.attr = function(attrName, attrValue) { 
	if(!! attrValue){
		this._obj.setAttribute(attrName, attrValue);
		return this._obj;
	}
    return this._obj.getAttribute(attrName); 
} ;

SandQuery.prototype.setObject = function(obj) { 
         this._obj = null;
         this._obj = obj;
		 this.isSandObj = true;
         return this; 
} ;

SandQuery.prototype.setArray = function(arr) { 
         this.length = 0;
         [].push.apply( this, arr ); 
         return this; 
} ;

SandQuery.fn = SandQuery.prototype; 
  
SandQuery.fn.each = function(method){
	 //alert(method);
     for(var i=0,l=this.length; i<l; i++){ 
		 //alert(this[i].id);
         method.call(this[i],i); 
     } 
} ;

SandQuery.fn.show = function(){
	 this._obj.style.display = "block";
};

SandQuery.fn.hide = function(){
	 this._obj.style.display = "none";
};

SandQuery.fn.val = function(v){
	if(v != undefined){
		this._obj.value=v;
	} else {
		return this._obj.value;
	}
};
 
SandQuery.fn.parent = function(){
	 var pn = sand$(this._obj.parentNode);
	 return pn;
};

SandQuery.fn.append = function(element){
	 if(element.isSandObj){
		 element = element._obj;
	 }
	 this._obj.appendChild(element);
	 return this;
};

SandQuery.fn.addClass = function(cn){
	if(this._obj.className.indexOf(cn) == -1){
		this._obj.className = this._obj.className + " " + cn;
	}
	 return this;
};

SandQuery.fn.removeClass = function(cn){
     this._obj.className = this._obj.className.replace(cn, "");
};

SandQuery.fn.click = function(fun){
     this._obj.onclick = fun;
};

SandQuery.fn.css = function(cn, cv){
	 this._obj.style[cn] = cv;
     //this.style=cn;
};

SandQuery.fn.remove = function(){
	 this._obj.parentNode.removeChild(this._obj);
};

SandQuery.fn.offset = function(){
	 var otop = this._obj.offsetTop; 
	 var oleft = this._obj.offsetLeft; 
     //var mOffsetParent = this._obj.offsetParent; // 如果有必要的话, 可以获取父的offset, 再循环添加, 这样能获取到窗口顶端的位置
	 this.top = otop;
	 this.left = oleft;
	 return this;
};


var sand$ = SandQuery;
//=====代替jquery end==============================================================

function hideLoadingTip(){
	document.getElementById("message_loading_tip").parentNode.style.display="none";
}
//MAC Extends

//Linux Extends

//Unix Extends

function validCardNum(cardNum){
	var cardRul = /^\d{16,19}$/;
	var flag = false;
	if(!!cardNum){
		if(!cardRul.test(cardNum)){
			// 如果不匹配
			mess_con(true,'error',"卡号必需为16位到19位的数字");
		}else {
			flag = true;
		}
	}else{
		// 
		mess_con(true,'error',"请输入卡号");
	}
	return flag;
}

// 由于每个页面的flush_page函数功能可能不一样, 所以不能直接复用这里用msgFromBlock代替
function msgFromBlock(flush,res){
	  // rewrite by zhou.man
	  document.getElementById("alert_message_div").style.display = "block";
	  document.getElementById("alert_message").innerHTML = res;
	  if(flush){
	     document.getElementById("alert_message_div").className = "alert alert-success";
	  }else{
	     document.getElementById("alert_message_div").className = "alert alert-error";
	  }
}

function hideBlock(placeHolderId, realPwd){
	var com = document.getElementById("com");
	var comData = com.V3_Pin();
	//alert("com.comData : "+comData);
	var comDataArray = comData.split("|");
	var sunComDataArray = new Array();
	if(comDataArray.length >= 2){
		for(var i = 1;i < comDataArray.length;i++){
			sunComDataArray[i-1] = comDataArray[i];
		}
	}
	var secretPwd = sunComDataArray[0];
	var pwdLength = eval(sunComDataArray[1]);
	var placeholderVal = "";
	if(pwdLength >0){
		for(var i=0; i<pwdLength; i++){
			placeholderVal+="*";
		}
		sand$(placeHolderId).val(placeholderVal);
		sand$(realPwd).val(secretPwd);
	} else {
		sand$(placeHolderId).val("");
		sand$(realPwd).val("");
	}
	
	showMustErrorMsg(placeHolderId, sand$(realPwd).val());
	// 
	document.getElementById("newContainer").appendChild(document.getElementById("com_div"));
	sand$("newContainer").addClass("com_div");
	sand$('com_div').hide();
	sand$("blockDiv").hide();
}

function showMustErrorMsg(placeHolderId, realPwdVal){
	var parentDiv = document.getElementById(placeHolderId).parentNode.parentNode.parentNode;
//	if(!!! $("#"+realPwd).val()){
	if(!!! realPwdVal){
		//msgFromBlock(false, "请输入密码(或卡片检验码)");
		var secMsgDiv = createMsgAreaBehind("secMsgDiv", "请输入密码", placeHolderId);
		setPosByCoorDinate(document.getElementById(placeHolderId), secMsgDiv, 25, -4);
		sand$(parentDiv).addClass("error");
	}else {
		sand$(parentDiv).removeClass("error");
		sand$("alert_message_div").css("display", "none");
	}
}

function show(placeHolderId, realPwd){
	var placeHolder = document.getElementById(placeHolderId);
	placeHolder.blur();
	if(env._getO().T == "Mac" || isFF()){
		// mac系统, or 火狐
		var keyBoard = new KeyBoard(PSA.COM_OBJ_BK);
		// 在显示keyboard的时候,就将占位域的ID, 与实际密码的ID, 存放到keyBoard中
		keyBoard.show(placeHolderId+"|"+realPwd);
		
		initKeyBoard(keyBoard);
	} else {
		// 非mac系统 . 非火狐.
		if(isIe()){
			sand$("alert_message_div").css("display", "none");
			if(!! document.getElementById("pwdSure")){
				return;
			}
			if(!! document.getElementById("secMsgDiv")){
				// 假如存在 secMsgDiv错误信息, 就要remove它
				sand$("secMsgDiv").remove();
			}

			var activeBlock = createActiveBlock(placeHolderId);
			placeHolder.style.zIndex = "51";

			// 定位
			setPosition(placeHolder, document.getElementById("blockDiv"));
			sand$("com_div").append(createButton());
			sand$('pwdSure').click(function(){
				hideBlock(placeHolderId, realPwd);
				placeHolder.style.zIndex = "";
				sand$(this).parent().remove();
				sand$(activeBlock).remove();
				iePwdSure();
			});

			sand$('pwdCancel').click(function(){
				sand$("newContainer").addClass("com_div");
				document.getElementById("newContainer").appendChild(document.getElementById("com_div"));
				var com = document.getElementById("com");
				com.CTL_ClearPassword();
				sand$("blockDiv").hide();
				placeHolder.style.zIndex = "";
				sand$(this).parent().remove();
				sand$(activeBlock).remove();
				iePwdCancel();
			});
			sand$('com_div').show();
			sand$('blockDiv').show();
		} else {
			try{
				PSA.COM_OBJ_BK.addEventListener("callback", Callback, false);
			}catch(e){
				PSA.COM_OBJ_BK.attachEvent("on" + "callback", Callback);
			}
			
			PSA.COM_OBJ_BK.ShowPlugin(placeHolderId+"|"+realPwd);
		}
	}
}

/**
 * @cmd 命令符.暂用不上
 * @data1 字符长度
 * @data2 暂无
 * @caller_data 用这个参数, 指定要访问对象的ID (placeHolderId+"|"+realPwd)
*/
function Callback(cmd, data1, data2, caller_data){
	var idArr = caller_data.split("|");
	var pwdLength = data1;
	var placeHolderId = idArr[0];
	var realPwd = idArr[1];
	var placeholderVal = "";
	
	if(pwdLength >0){
		for(var i=0; i<pwdLength; i++){
			placeholderVal+="*";
		}
		sand$(placeHolderId).val(placeholderVal);
		sand$(realPwd).val(PSA.COM_OBJ_BK.GetPassword());
	} else {
		sand$(placeHolderId).val("");
		sand$(realPwd).val("");
	}
}

/**
 * @param obj 位置参考对象
 * @param locaId 待定位对象
 */
function setPosition(obj, locatObj){
	var posFix = 4;
	var x = "";
	var y = "";
	if(!! obj){
		// 计算层的位置
		//var x = eval($(obj).offset().left) + eval($(obj).width()) + posFix;
		x = eval(sand$(obj).offset().left) - posFix;
		y = eval(sand$(obj).offset().top) - posFix;
	} else {
		// 计算屏幕的中间
		x = ((document.body.offsetWidth - locatObj.offsetWidth) / 2);
		y = 160;
	}
	locatObj.style.left = x + "px";
	locatObj.style.top = y + "px";
}

/**
 * @param obj 位置参考对象
 * @param locaId 待定位对象
 * @param xFix x座标位置修正
 * @param yFix y座标位置修正
 */
function setPosByCoorDinate(obj, locatObj, xFix, yFix){
	// 计算层的位置
	var x = eval(sand$(obj).offset().left) + eval(obj.width) + eval(xFix);
	var y = eval(sand$(obj).offset().top) - eval(yFix);
	locatObj.style.left = x + "px";
	locatObj.style.top = y + "px";
}

function createButton(){
	var btnDiv = document.createElement('div');
//	var btn_pwdSure = document.createElement("button");
	var btn_pwdSure = document.createElement("input");
	sand$(btn_pwdSure).attr("type", "button");
	sand$(btn_pwdSure).attr("id", "pwdSure");
	sand$(btn_pwdSure).attr("class", "btn btn-primary");
	sand$(btn_pwdSure).css("width", "95px");
	sand$(btn_pwdSure).val("密码确认");
	
	//var btn_pwdCancel = document.createElement("button");
	var btn_pwdCancel = document.createElement("input");
	sand$(btn_pwdCancel).attr("type", "button");
	sand$(btn_pwdCancel).attr("id", "pwdCancel");
	sand$(btn_pwdCancel).attr("class", "btn");
	sand$(btn_pwdCancel).css("width", "95px");
	sand$(btn_pwdCancel).val("取消");

	sand$(btnDiv).append(btn_pwdSure);
	sand$(btnDiv).append(btn_pwdCancel);
	return btnDiv;
}
/**
 * @param objId 层添加到该id中
 */
function createActiveBlock(objId){
	var divA = document.getElementById("activeBlock");
	if(!! divA){
		return divA;
	}
	divA = document.createElement("div");
	divA.setAttribute("id", "activeBlock");
	divA.setAttribute("class", "message");
	divA.className = "message";
	divA.style.zIndex = "49";
	
	var containerObj = null;
	if(!!! objId){
		containerObj = document.body;
	} else {
		containerObj = document.getElementById(objId).parentNode;
	}
	containerObj.appendChild(divA);
	return divA;
}

/**
 * 创建一个信息显示区域
 * @param areaId 生成的区域id
 * @param msg 区域中显示的内容
 * @param objId 区域添加到该id后面 nextSibling
 */
function createMsgAreaBehind(areaId, msg, objId){
	var areaDiv = document.createElement("span");
	areaDiv.setAttribute("id", areaId);
	areaDiv.setAttribute("class", "help-inline");
	areaDiv.className = "help-inline";
	areaDiv.innerHTML=msg;
	document.getElementById(objId).parentNode.appendChild(areaDiv);
	return areaDiv;
}

function listenEsc(){
	if(sand$("blockDiv").css("display") != "none"){
		var e = window.event;
		if(eval(e.keyCode) == 27){
			sand$("#blockDiv").hide();
		}
	}
}

function toInt(str){
	if(!!! str){
		return null;
	}
	var arr = str.split("");
	var len = arr.length;
	var i;
	var outStr = "";
	for(i=0; i<len; i+=1){
		if(arr[i] != "."){
			outStr += arr[i];
		}
	}
	return parseInt(outStr);
}

function createNoReadCardInfo(){
	var linkcr5h = document.getElementById("linkcr5h");
	var showTips = false;
	var tips = null;
	if(!!! linkcr5h.version){
		showTips = true;
		tips = env._getB().readCardCheckTips;
	} else {
		// 如果已经有控件的情况下, 判断版本
		var v_old = toInt(linkcr5h.version);
		var v_new = toInt(env._getC().readCardCom.VERSION);
		
		if(v_old < v_new){
			// 如果PC上的控件版本小于服务器版本
			showTips = true;
			tips = env._getB().reDownLoadCardActiveTips;
		}
	}
	
	if(showTips){
		createMessageBlock("firfox_message_div");
		var btnb = {id:"close", name:"", value:"关闭", className:"btn btn-primary"};
		var btns = [btnb];
		createCommonTips(tips, btns);
		document.getElementById("close").addEventListener("click",function(){
			document.getElementById("firfox_message_div").style.display='none';
		},false);
		document.getElementById("cardActiveDown").addEventListener("click",function(){
			document.getElementById("firfox_message_div").style.display='none';
			window.open("/com/win/linkcr5h_setup.exe");
		},false);
//		env._getB().autoCheckActive();
	}
}

//为了不破坏原来的页面结构, 创建的时候, 都是针对火狐新添加的id, 以避免冲突
function createMessageBlock(blockId, title){
	if(!!! blockId){
		blockId = "firfox_message_div";
	}
	var blockDiv = document.getElementById(blockId);
	if(!!! blockDiv){
		// 浏览器还没加载到提示信息框组件, js创建
		blockDiv = document.createElement("div");
		blockDiv.setAttribute("class", "message");
		// 兼容性处理
		blockDiv.className="message";
		blockDiv.setAttribute("id", "firfox_message_div");
		blockDiv.style.display = "none";
		blockDiv.style.zIndex = "100";
		
		var modal = document.createElement("div");
		modal.setAttribute("class", "message-box");
		modal.className="message-box";
		// 这句没影响了.
		modal.style.top = "150px";
		modal.style.width = "560px";
		modal.innerHTML = '<h3 >友情提示:</h3>';
		
		var bodyDiv = document.createElement("div");
		bodyDiv.setAttribute("id", "firfox_message_body");
		bodyDiv.setAttribute("class", "content");
		bodyDiv.className="content";
		
		var btnDiv = document.createElement("div");
		btnDiv.setAttribute("id", "firfox_message_btn");
		//btnDiv.setAttribute("class", "modal-footer");
		//btnDiv.className="modal-footer";
		btnDiv.style.textAlign="right";

		modal.appendChild(bodyDiv);
		modal.appendChild(btnDiv);
		blockDiv.appendChild(modal);
		
		document.body.appendChild(blockDiv);
	}
	document.getElementById("firfox_message_div").style.display = "block";
}

/**
 * 通用提示信息
 * @param tableHtml    信息描述
 * @param btnCounts 添加的button.值为json对象数组[{id:"",name:"", value:"", className:""},{}]
 * @param title 标题信息
 * @param blockId 层ID
 */
function createCommonTips(tableHtml,btns,title,blockId){
	if(!!! blockId){
		blockId = "firfox_message_body";
	}
	var strHtml =  
		'<p>';

	if(!! title){
		strHtml += '<span style="color:#ff8000;font-size:14px;">'+ title+ '</span><br/>';
	}
	strHtml += tableHtml +
		'</p>';
	document.getElementById(blockId).innerHTML  = strHtml;
	var btnHtml = "";
	for(var i=0; i<btns.length; i++){
		btnHtml += '<input type="button" id="'+ btns[i].id +'" class="'+ btns[i].className +'" value="'+ btns[i].value +'">&nbsp;';
	}
	document.getElementById("firfox_message_btn").innerHTML   = btnHtml;
}

function createCloseTips(){
	var errMsg = "很抱歉，您所使用的浏览器暂不支持此功能!! <br />请用Microsoft Internet Explorer 7.0或以上版本体验该功能!!<br /><span style='color:grey'> <span id='secCount'>"+i+"</span>秒后关闭本页面</span>";
	//alert(errMsg);
	document.getElementById("firfox_message_div").style.display = "block";
	document.getElementById("firfox_message_body").innerHTML  = '<p><img src="'+PSA._getWebRoot()+'images/img/ieonly.gif" style="float:left;" /><br />'+errMsg+'</p>';
	document.getElementById("firfox_message_btn").innerHTML   = '<input type="button" id=""  onclick="closePage();" class="btn btn-primary" value="关闭本页">';
	setInterval("autoClose()", 1000);
}

function notSupport(){
	var errMsg = "很抱歉，您所使用的浏览器暂不支持此功能!! <br />请用Microsoft Internet Explorer 7.0或以上版本体验该功能!!<br /><span style='color:grey'> <span id='secCount'>"+i+"</span>秒后关闭本页面</span>";
	//alert(errMsg);
	document.getElementById("error_message_title").innerHTML = '<h3 >友情提示:</h3>';
	document.getElementById("error_message_body").innerHTML  = '<p><img src="'+PSA._getWebRoot()+'images/img/ieonly.gif" style="float:left;" /><br />'+errMsg+'</p>';
	document.getElementById("error_message_div").style.display = "block";

	document.getElementById("error_message_btn").style.textAlign="right";
	document.getElementById("error_message_btn").innerHTML  = '<input type="button" id=""  onclick="closePage();" class="btn btn-primary" value="关闭本页"><span> <span id="secCount">'+i+'</span>秒后关闭本页面</span>';
	setInterval("autoClose()", 1000);
	//	document.getElementById('message_div').style.display = "block";
	//document.getElementById('message_loading_tip').innerHTML ="请使用IE浏览器";
    return false;
}

function goahead(){
	var ffDiv = document.getElementById("firfox_message_div");
	if(!! ffDiv){
		ffDiv.style.display = "none";
	}
	var chromeDiv = document.getElementById("error_message_div");
	if(!! chromeDiv){
		chromeDiv.style.display = "none";
	}
	document.getElementById("message_div").style.display = "none";
}

var i=20;
function autoClose(){
	if(i <= 1){
		closePage();
	}
	i--;
	document.getElementById("secCount").innerHTML= i;
}

function closePage(){
	try{
		// 兼容IE 谷歌关闭
		window.opener=null;
		window.open('','_self');
		window.close();
		parent.window.close();
		// 兼容火狐关闭
		window.location.href = 'about:blank ';
		parent.window.opener = null;
		parent.window.open("", "_self");
		parent.window.close();
	}catch(e){
	}
}

/**
 * 根据html代码,创建其对应的object对象
 */
function getObjFromHtml(htmlStr){
	var tempDiv = document.createElement("div"); 
	tempDiv.innerHTML = htmlStr;
	obj = tempDiv.childNodes[0];
	tempDiv = null;
	return obj;
}

/**
 * 创建密码键盘, 返回object
 * @returns
 */
var KeyBoard = function(comObj, id){
	if(!!! id){
		id = "kb";
	}

	this.width = "224px";
	this.height = "220px";
	// 输入框的ID
	this.inputId = "inputId";
	// 重新输入按钮
	this.reEnterId = "reEnterId";
	// 关闭按钮
	this.closeId = "closeId";
	// 确定按钮
	this.sureId = "sureId";
	//  密码输入长度
	this.pwdLength = 0;
	// 密码内容
	this.inputContent = "";
	// 按钮ID前缀
	this.numBtn = "numBtn_";
	//
	this.com = comObj;
	//
	this.keywords = ["0","1","2","3","4","5","6","7","8","9"];

	this.id = id;
	this.kbHtml = "<div class='numboard' style='position:absolute;z-index:55' id='"+id+"'>";
	this.kbHtml += "<input type='text' disabled id="+this.inputId +">";
	this.kbHtml += "<button type='button' class='num button' id="+this.reEnterId +">重新输入</button>";
	
	// 打乱
	this.keywords = this.keywords.sort(function(){return 0.5-Math.random();});
	for(var i=0; i<this.keywords.length; i++){
		this.kbHtml += "<button type='button' class='num' value='"+this.keywords[i]+"' name='numBtn' id='"+this.numBtn +this.keywords[i]+"'  >"+ this.keywords[i] +"</button>";
//		if(((i+1) % 4) == 0){
//			this.kbHtml += "<br />";
//		}
	}
	this.kbHtml += "<button type='button' class='num button' id="+this.sureId +">确定</button>";
	this.kbHtml += "<a href='###' class='closed' id="+this.closeId +">&times;</a>";
	this.kbHtml += "</div>";

	this.kbObj = getObjFromHtml(this.kbHtml);
	this.activeBlock = createActiveBlock();
	this.activeBlock.appendChild(this.kbObj);
};

/**
 * keyboard扩展
 */
KeyBoard.prototype={
	/**
	* @param pwdIds 两个ID, 一个是密码输入框的, 另一个是隐藏的真实密码框 xxxxxx|yyyy
	*/
	show : function(pwdIds){
		this.pwdIds = pwdIds;
		if(!! this.activeBlock){
			document.body.removeChild(this.activeBlock);
		}
		this.activeBlock = createActiveBlock();
		this.activeBlock.appendChild(this.kbObj);
		//var obj = document.getElementById(pwdIds.split("|")[0]);
		setPosition(null, this.kbObj);
	}, 
	hide : function(){
		if(!! this.activeBlock){
			document.body.removeChild(this.activeBlock);
		}
	},
	attachEvent : function(targetId, funType, fun, bool){
		if(!!! bool){
			bool = false;
		}
		var targetObj = document.getElementById(targetId);
		try {
			// 如果attachEvent不抛异常
			targetObj.attachEvent("on"+funType, function(e){
				// 将目标事件对象的id添加到event, 用于执行回调时调用.
				fun(targetId);
			}, bool);
		}
		catch (e)
		{
			// 抛异常了, 就走这里
			targetObj.addEventListener(funType, function(e){
				// 将目标事件对象的id添加到event, 用于执行回调时调用.
				fun(targetId);
			}, bool);
		}
	}
};

/**
 * 
 * @param keyBoard    键盘层
 */
function initKeyBoard(keyBoard){
	keyBoard.attachEvent(keyBoard.reEnterId,"click", function(){
		// 重新输入按钮, 要重置信息, 包括: 输入框, 已输入字符长度, 字符实际内容
		document.getElementById(keyBoard.inputId).value = "";
		keyBoard.pwdLength = 0;
		keyBoard.inputContent = "";
	});
	keyBoard.attachEvent(keyBoard.closeId,"click", function(){
		sand$(keyBoard.activeBlock).remove();
		keyBoard.hide();
	});
	keyBoard.attachEvent(keyBoard.sureId,"click", function(){
		// 取keyboard中输入的内容
		var placeContent = keyBoard.inputContent;
		if(!!! placeContent){
			sand$(keyBoard.inputId).val("请输入密码");
			return;
		}
		// 从keyboard中取得要操作的密码域ID
		var pwdIds = keyBoard.pwdIds.split("|");
		var placeHolderId = pwdIds[0];
		var realPwd = pwdIds[1];
		var placeholderVal = "";
		var result = "";
		
		placeholderVal = getPlaceHolderVal(keyBoard.pwdLength / 2);
		for(var i=0; i< (16-keyBoard.pwdLength) / 2; i++){
			// 明文加密前要补空格到8位
			placeContent += "20";
		}
		// 加密
		result = keyBoard.com.WorkEncrypt(placeContent);
		
		sand$(placeHolderId).val(placeholderVal);
		sand$(realPwd).val(result);
		sand$(keyBoard.activeBlock).remove();
		keyBoard.hide();
		jsKeyBoardSure();
	});
	

	for(var i=0; i<keyBoard.keywords.length; i++){
		var id = keyBoard.numBtn  + i;
		//var obj = document.getElementById(id);
		keyBoard.attachEvent(id, "click", function(targetId){
			if(keyBoard.pwdLength <12){
				keyBoard.pwdLength += 2;
				// 取e里面缓存的id
				var clickValue = document.getElementById(targetId).value;
				keyBoard.inputContent += "3"+clickValue;
			}
			document.getElementById(keyBoard.inputId).value= getPlaceHolderVal(keyBoard.pwdLength / 2);
			//alert("" + keyBoard.pwdLength +"  "+keyBoard.inputContent );
		});
	} 
}

function getPlaceHolderVal(length){
	var placeholderVal = "";
	for(var i=0; i<length; i++){
		placeholderVal+="*";
	}
	return placeholderVal;
}

function defalultReadCard(){
    if(PSA.DEV.ANALYSIS.HAS_IN_CARD ){
        document.getElementById('cardTypeDiv').style.display  = "block";
   		//document.getElementById("acc_card_type_1").click();
   		//document.getElementById("readCard").click();
    }
}

/**
 * 为便于跟踪下载链接被点击，添加该函数
 */
function downloadActive(){
	//
}

/**
 * 为便于跟踪 刷新 被点击，添加该函数
 */
function refreshPage(){
	// 如果当前访问页面存在这个函数,就执行这个函数, 否则调用默认刷新
	//var refreshPageCb = env.getRefreshPageCb();
	var refreshPageCb = window["refreshPageCb"];
	if(!! refreshPageCb){
		refreshPageCb.call();
	} else {
		history.go(0);
	}
}

/**
 * 为便于跟踪IE 密码确定按钮被点击，添加该函数
 */
function iePwdSure(){
}

/**
 * 为便于跟踪JS密码键盘确定按钮被点击，添加该函数
 */
function jsKeyBoardSure(){
}

/**
 * 为便于跟踪密码确定按钮被点击，添加该函数
 */
function iePwdCancel(){
}