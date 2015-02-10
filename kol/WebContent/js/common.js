
(function(window){
	var $_d = function(i){
		if ( window == this ){
			return new $d(i);
		}
		if(typeof i == "object"){
			this._$_d = i;
		} else if(typeof i == "string"){
			this._$_d = $$(i);
		} else {
			// for array
			this._$_d = i;
		}
		this.copyAttr();
	},
	$$ = function(id){
		return document.getElementById(id);
	};

	$_d.prototype.copyAttr = function(o) {
		if(!!! this._$_d) {
			return false;
		}
		if(!!! o) {
			// if param object is null , copy owner object's attributes
			o = this._$_d;
		}
		var i = 0;
		for(var k in o) {
			i += 1;
			this[k] = o[k];
		}
		return this;
	};
	
	$_d.prototype.remove = function() {
		if(!!! this._$_d){
			return;
		}
		this._$_d.parentNode.removeChild(this._$_d);
		return this;
	};
	
	$_d.prototype.offset = function() {
		// 经过属性复制后,已经有一切属性
		this.top = this.offsetTop;
		this.left = this.offsetLeft;
		return this;
	};
	
	$_d.prototype.submit = function() {
		// 经过属性复制后,已经有一切属性
		$$(this.id).submit();
	};
	
	$_d.prototype.hide = function() {
		// 经过属性复制后,已经有一切属性
		this.style.display = this._$_d.style.display = "none";
		return this;
	};
	
	$_d.prototype.show = function() {
		// 经过属性复制后,已经有一切属性
		this.style.display = this._$_d.style.display = "block";
		return this;
	};
	
	$_d.prototype.val = function() {
		var thisVal = "";
		if(this.value != null && this.value != undefined){
			thisVal = this.value;
		} else if(this.text != null && this.text != undefined){
			thisVal = this.text;
		} else {
			thisVal = this.innerHTML;
		}
		return thisVal;
	};
	
	/**
	 * 创建一个信息显示区域
	 * @param areaId 生成的区域id, 注意不同的信息间不可有相同的ID.否则效果不出来
	 * @param msg 区域中显示的内容
	 * @param objId 区域添加到该id后面 nextSibling
	 */
	
	$_d.prototype.createMsgAreaBehind = function(areaId, msg, objId) {
		var areaDiv = $$(areaId);
		if(!!! areaDiv){
			// 如果不存在的情况下
			areaDiv = document.createElement("span");
			areaDiv.style.dispay = "none;";
			areaDiv.setAttribute("id", areaId);
			areaDiv.setAttribute("class", "help-inline");
			areaDiv.className = "help-inline";
			areaDiv.innerHTML=msg;
			$$(objId).parentNode.appendChild(areaDiv);
		}
		return areaDiv;
	};
	

	/**
	 * @param obj 位置参考对象
	 * @param locaId 待定位对象
	 * @param xFix x座标位置修正
	 * @param yFix y座标位置修正
	 */
	$_d.prototype.setPosByCoorDinate = function(obj, locatObj, xFix, yFix) {
		// 计算层的位置
		var x = eval(obj.offsetTop) + eval(obj.width) + eval(xFix);
		var y = eval(obj.offsetLeft) - eval(yFix);
		locatObj.style.left = x + "px";
		locatObj.style.top = y + "px";
	};
	
	/**
	 * @param id
	 * @param tips;
	 * @param showTips defalult true;
	 * @returns {Boolean}
	 */
	$_d.prototype.checkValueSame = function(id, tips, showTips) {
		if(!!! id){
			return ;
		}
		var idobj = $$(id);
		if(!!! idobj){
			return;
		}
		
		if(!!! showTips){
			showTips = true;
		}
		
		if(!!! tips){
			// default tip
			tips = "not same";
		}
		
		var evalStr = "'" + this.val()+ "' === '" + idobj.value + "'";
		var isSame = Boolean(eval(evalStr));

		var thisId = this.id;
		var msgAreaId_A = thisId + "Area";
		var msgAreaId_B = id + "Area";

		var a = $$(msgAreaId_A);
		var b = $$(msgAreaId_B);
		if(!! a){
			a.parentNode.removeChild(a);
		}
		if(!! b){
			b.parentNode.removeChild(b);
		}
		if(!isSame){
			this.createMsgAreaBehind(msgAreaId_A, tips, thisId);
			this.createMsgAreaBehind(msgAreaId_B, tips, id);
		}
		return isSame;
	};

	$_d.prototype.notNull = function(id, msg) {
		var a = this._$_d;
		if(!! id){
			a = $$(id);
		}
		if(!!! msg){
			msg = "not null";
		}
		var tipId = "notNull_"+a.id;
		var b = $$(tipId);
		if(!! b) {
			b.parentNode.removeChild(b);
		}
		if(!! a.value){
			return true;
		} else {
			var _notNull = this.createMsgAreaBehind(tipId, msg, a.id);
			this.setPosByCoorDinate(a, _notNull, 25, -4);
			return false;
		}
	};
	
	window.$d = $_d;
})(window);

var notNullArr = function (ids){
	var i;
	var len = ids.length;
	// 默认true, 表示非空
	var a = true;
	for(i=0; i<len; i+=1){
		if(!$d(ids[i]).notNull()){
			a = false;
		}
	}
	return a;
};

function createButton(){
	var btnDiv = $d('div');
//	var btn_pwdSure = document.createElement("button");
	var btn_pwdSure = document.createElement("input");
	$(btn_pwdSure).attr("type", "button");
	$(btn_pwdSure).attr("id", "pwdSure");
	$(btn_pwdSure).attr("class", "btn btn-primary");
	$(btn_pwdSure).css("width", "95px");
	$(btn_pwdSure).val("密码确认");
	
	//var btn_pwdCancel = document.createElement("button");
	var btn_pwdCancel = document.createElement("input");
	$(btn_pwdCancel).attr("type", "button");
	$(btn_pwdCancel).attr("id", "pwdCancel");
	$(btn_pwdCancel).attr("class", "btn");
	$(btn_pwdCancel).css("width", "95px");
	$(btn_pwdCancel).val("取消");

	$(btnDiv).append(btn_pwdSure);
	$(btnDiv).append(btn_pwdCancel);
	return btnDiv;
}

function getObjFromHtml(htmlStr){
	var tempDiv = document.createElement("div"); 
	tempDiv.innerHTML = htmlStr;
	obj = tempDiv.childNodes[0];
	tempDiv = null;
	return obj;
}

