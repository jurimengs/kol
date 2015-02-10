/* JS AOP 
 * 注：必需要在函数执行之前，先执行切入函数，如果函数已经执行，再调用切入函数则无效 
 * */

function initParam(params){
	params = params || {};
	params["browser"] = env._getB().T;
	params["currentPage"] = document.URL;
	params["userAgent"] = navigator.userAgent.toLowerCase();
}

var SANDPAY = (function() {
	aop = {
		before : function (fnName , beforeFn, params, windowObj) {
			if(!!! windowObj){
				windowObj = window;
			}
			if(typeof(windowObj) == 'function') {
				windowObj = windowObj.prototype; 
			}
			if(typeof(windowObj[fnName]) != 'function') {
				return ; 
			}
			if(typeof(beforeFn) != 'function') {
				return ; 
			}
			initParam(params);
			var target = windowObj[fnName];
			//alert(""+target);
			windowObj[fnName] = function () { 
				beforeFn.call(this,params); 
				// TODO 如何监控到目标函数的参数: arguments
				// TODO 看看这个arguments是不是
				return target.apply(this, arguments); 
			};
			//alert(fnName+"  "+typeof(windowObj[fnName]) +"  " +windowObj[fnName]);
		},
		after : function (fnName , afterFn , params, windowObj) { 
			if(!!! windowObj){
				windowObj = window;
			}
			if(typeof(windowObj) == 'function') {
				windowObj = windowObj.prototype ; 
			}
			if(typeof(windowObj[fnName]) != 'function') {
				return ; 
			}
			if(typeof(afterFn) != 'function') {
				return ; 
			}
			initParam(params);
			var target = windowObj[fnName] ;  
			windowObj[fnName] = function () { 
				var returnValue = target.apply(this, arguments); 
				afterFn.call(this, params); 
				return returnValue; 
			};
		} 
	};
    return this;
})();
window.SANDPAY = SANDPAY;


/**
*
*/
var listenerBef = function(params){
	//alert("listenerBef");
	params["listener"] = "listenerBef";
	ajaxRecord(params);
};

/**
* 
*/
var listenerAfter = function(params){
	//alert("listenerAfter");
	params["listener"] = "listenerAfter";
	ajaxRecord(params);
};

/**
 * @param params
 */
function ajaxRecord(params){
	params = params || {};
	params["rechargeAmt"] = $("#rechargeAmt").val();
	params["medium"] = $("#acc_cardno").val();
	$.ajax({
		type : "post",
		url : "/usercenter/jsListener.do",
		timeout : 45000,
		data : params,
		dataType : "json",
		success : function(){},
		error : function(){}
	});
};

function logObj(obj){
	var str ="";
	for (var k in obj){
		str += k +":"+obj[k] +"  ";
	}
	return str;
}

/**
* 跟踪用户操作
*/
var UserOperate = function(params){
	params["listener"] = "UserOperate";
	followRecord(params);
};

/**
* 跟踪用户操作
*/
function followRecord(params){
	params = params || {};
	$.ajax({
		type : "post",
		url : "/usercenter/jsListener.do",
		timeout : 45000,
		data : params,
		dataType : "json",
		success : function(){alert("调试，成功");},
		error : function(){}
	});
};

//监控下载
SANDPAY.aop.before("downloadActive",UserOperate, {
	"operate":"\u63A7\u4EF6\u4E0B\u8F7D"
});

// 监控手动刷新
SANDPAY.aop.before("refreshPage",UserOperate, {
	"operate":"\u9875\u9762\u672A\u81EA\u52A8\u5237\u65B0,\u624B\u52A8\u5237\u65B0"
});

//监控密码键盘显示
//由于控件自带键盘占据线程的原因，这里如果使用after，将需要等到控件键盘线程释放才能执行监控
SANDPAY.aop.before("show",UserOperate, {
	"operate":"\u663E\u793A\u5BC6\u7801\u952E\u76D8"
});

//监控IE 控件键盘密码确定（根据回调函数来跟踪）
SANDPAY.aop.before("iePwdSure",UserOperate, {
	"operate":"IE控件密码键盘确定"
});

//监控非IE键盘密码确定（控件自带键盘，根据回调函数来跟踪）
SANDPAY.aop.before("Callback",UserOperate, {
	"operate":"控件自带密码键盘确定"
});

//监控JS 键盘密码确定 
SANDPAY.aop.before("jsKeyBoardSure",UserOperate, {
	"operate":"JS密码键盘确定"
});