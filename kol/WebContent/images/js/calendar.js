/**
 * 返回日期
 * @param d the delimiter
 * @param p the pattern of your date
 */
String.prototype.toDate = function(x, p) {
  if(x == null) x = "-";
  if(p == null) p = "ymd";
  var a = this.split(x);
  var y = parseInt(a[p.indexOf("y")]);
  //remember to change this next century ;)
  if(y.toString().length <= 2) y += 2000;
  if(isNaN(y)) y = new Date().getFullYear();
  var m = parseInt(a[p.indexOf("m")]) - 1;
  var d = parseInt(a[p.indexOf("d")]);
  if(isNaN(d)) d = 1;
  return new Date(y, m, d);
};

/**
 * 格式化日期
 * @param   d the delimiter
 * @param   p the pattern of your date
 * @author  meizz
 */
Date.prototype.format = function(style) {
  var o = {
    "M+" : this.getMonth() + 1, //month
    "d+" : this.getDate(),      //day
    "h+" : this.getHours(),     //hour
    "m+" : this.getMinutes(),   //minute
    "s+" : this.getSeconds(),   //second
    "w+" : "天一二三四五六".charAt(this.getDay()),   //week
    "q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter
    "S"  : this.getMilliseconds() //millisecond
  };
  if(/(y+)/.test(style)) {
    style = style.replace(RegExp.$1,
    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for(var k in o){
    if(new RegExp("("+ k +")").test(style)){
      style = style.replace(RegExp.$1,
        RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
    }
  }
  return style;
};

function checkImport(){
	 panel = document.getElementById("calendarPanel");
 	 if(panel==null)
	 	alert("日历控件引用出错，请在<body>中引入calendar.js文件.");
}

/**
 * 日历类
 * @param   beginYear 1990
 * @param   endYear   2010
 * @param   lang      0(中文)|1(英语) 可自由扩充
 * @param   dateFormatStyle  "yyyy-MM-dd";
 * @version 2006-04-01
 * @author  KimSoft (jinqinghua [at] gmail.com)
 * @update
 */
function Calendar(beginYear, endYear, lang, dateFormatStyle) {
  this.beginYear = 1990;
  this.endYear = 2040;
  this.lang = 0;            //0(中文) | 1(英文)
  this.dateFormatStyle = "yyyy-MM-dd";

  if (beginYear != null && endYear != null){
    this.beginYear = beginYear;
    this.endYear = endYear;
  }
  if (lang != null){
    this.lang = lang;
  }

  if (dateFormatStyle != null){
    this.dateFormatStyle = dateFormatStyle;
  }

  this.dateControl = null;
  this.panel = document.getElementById("calendarPanel");
  checkImport();
  this.form  = null;
  this.date = new Date();
  this.year = this.date.getFullYear();
  this.month = this.date.getMonth();

  this.colors = {
  "cur_word"      : "#FFFFFF",  //当日日期文字颜色
  "cur_bg"        : "#E3E3E3",  //当日日期单元格背影色
  "sun_word"      : "#4163A9",  //星期天文字颜色
  "sat_word"      : "#4163A9",  //星期六文字颜色
  "td_word_light" : "#333333",  //单元格文字颜色
  "td_word_dark"  : "#CCCCCC",  //单元格文字暗色
  "td_bg_out"     : "#ffffff",  //单元格背影色
  "td_bg_over"    : "#D0D9EA",  //单元格背影色
  "tr_word"       : "#000000",  //日历头文字颜色
  "tr_bg"         : "#E9EDF5",  //日历头背影色
  "input_border"  : "#3F5F9A",  //input控件的边框颜色
  "input_bg"      : "#859CCD",   //input控件的背影色
  "curSel_bg"     : "#B4C2DE"  //上次选中日期颜色
  };

  this.draw();
  this.bindYear();
  this.bindMonth();
  this.changeSelect();
  this.bindData();
}

/**
 * 日历类属性（语言包，可自由扩展）
 */
Calendar.language = {
  "year"   : [[""], [""]],
  "months" : [["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
        ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"]
         ],
  "weeks"  : [["日","一","二","三","四","五","六"],
        ["SUN","MON","TUR","WED","THU","FRI","SAT"]
         ],
  "clear"  : [["清空"], ["CLS"]],
  "today"  : [["今天"], ["TODAY"]],
  "close"  : [["关闭"], ["CLOSE"]]
};

Calendar.prototype.draw = function() {
  calendar = this;
  var mvAry = [];
  var cellCss=" border-right: 1px solid #9EB5DA; border-left: 1px solid #FFFFFF; border-top:1px solid #FFFFFF; border-bottom: 1px solid #9EB5DA; cursor:pointer;";
  mvAry[mvAry.length]  = '    <table width="100%" border="0" style="'+cellCss+'background-color:#F1F4FA;" cellpadding="0" cellspacing="1">';
  mvAry[mvAry.length]  = '      <tr>';
  mvAry[mvAry.length]  = '        <th align="left" height="24px" width="1%"><input style="color:#fff;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:16px;height:20px; cursor:pointer;" name="prevMonth_z" type="button" id="prevMonth_z" value="&lt;" /></th>';
  mvAry[mvAry.length]  = '        <th align="center" width="98%" nowrap="nowrap"><select name="calendarYear_z" id="calendarYear_z" style="font-size:12px;background-color: #FBFDFF;"></select>&nbsp;<select name="calendarMonth_z" id="calendarMonth_z" style="font-size:12px;background-color: #FBFDFF;"></select></th>';
  mvAry[mvAry.length]  = '        <th align="right" width="1%"><input style="color:#fff;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:16px;height:20px;cursor:pointer;" name="nextMonth_z" type="button" id="nextMonth_z" value="&gt;" /></th>';
  mvAry[mvAry.length]  = '      </tr>';
  mvAry[mvAry.length]  = '    </table>';
  mvAry[mvAry.length]  = '    <table id="calendarTable" width="100%" style="border:0px solid #CCCCCC;background-color:#BBDDFF;" border="0" cellpadding="3" cellspacing="0">';
  mvAry[mvAry.length]  = '      <tr>';
  for(var i = 0; i < 7; i++) {
    mvAry[mvAry.length]  = '      <th style="'+cellCss+'font-size:13px;font-weight:normal;background-color:' + calendar.colors["tr_bg"] + ';color:' + calendar.colors["tr_word"] + ';">' + Calendar.language["weeks"][this.lang][i] + '</th>';
  }
  mvAry[mvAry.length]  = '      </tr>';
  for(var i = 0; i < 6;i++){
    mvAry[mvAry.length]  = '    <tr align="center">';
    for(var j = 0; j < 7; j++) {
      if (j == 0){
        mvAry[mvAry.length]  = '  <td style="line-height:14px;'+cellCss+'background-color: #FBFDFF;font-size:12px;cursor:default;color:' + calendar.colors["sun_word"] + ';"></td>';
      } else if(j == 6) {
        mvAry[mvAry.length]  = '  <td style="line-height:14px;'+cellCss+'background-color: #FBFDFF;font-size:12px;cursor:default;color:' + calendar.colors["sat_word"] + ';"></td>';
      } else {
        mvAry[mvAry.length]  = '  <td style="line-height:14px;'+cellCss+'background-color: #FBFDFF;font-size:12px;cursor:default;"></td>';
      }
    }
    mvAry[mvAry.length]  = '    </tr>';
  }
  mvAry[mvAry.length]  = '  </table><table width="100%" border="0" style="'+cellCss+'background-color:#F1F4FA;" cellpadding="0" cellspacing="1">';
  mvAry[mvAry.length]  = '    <tr style="background-color:#F1F4FA;">';
  mvAry[mvAry.length]  = '        <th width="30%" height="24px"><input name="calendarToday_z" type="button" id="calendarToday_z" value="' + Calendar.language["today"][this.lang] + '" style="color:#fff;cursor:pointer;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:90%;height:20px;font-size:12px;"/></th>';
  mvAry[mvAry.length]  = '        <th width="30%" height="24px"><input name="calendarClear_z" type="button" id="calendarClear_z" value="' + Calendar.language["clear"][this.lang] + '" style="cursor:pointer;color:#fff;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:90%;height:20px;font-size:12px;"/></th>';
  mvAry[mvAry.length]  = '        <th width="30%" height="24px"><input name="calendarClose_z" type="button" id="calendarClose_z" value="' + Calendar.language["close"][this.lang] + '" style="cursor:pointer; color:#fff;border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:90%;height:20px;font-size:12px;"/></th>';
  mvAry[mvAry.length]  = '      </tr>';
  mvAry[mvAry.length]  = '    </table>';
  
  this.panel.innerHTML = mvAry.join("");

  document.getElementById("prevMonth_z").onclick = function () {calendar.goPrevMonth(this);};
  document.getElementById("nextMonth_z").onclick = function () {calendar.goNextMonth(this);};

  document.getElementById("calendarClear_z").onclick = function () {calendar.dateControl.value = "";calendar.hide();};
  document.getElementById("calendarClose_z").onclick = function () {calendar.hide();};
  document.getElementById("calendarYear_z").onchange = function () {calendar.update(this);};
  document.getElementById("calendarMonth_z").onchange = function () {calendar.update(this);};
  document.getElementById("calendarToday_z").onclick = function () {
    var today = new Date();
    calendar.date = today;
    calendar.year = today.getFullYear();
    calendar.month = today.getMonth();
    calendar.changeSelect();
    calendar.bindData();
    calendar.dateControl.value = today.format(calendar.dateFormatStyle);
    calendar.hide();
  };

};

//年份下拉框绑定数据
Calendar.prototype.bindYear = function() {


  var cy = document.getElementById("calendarYear_z");
  cy.length = 0;
  for (var i = this.beginYear; i <= this.endYear; i++){
    cy.options[cy.length] = new Option(i + Calendar.language["year"][this.lang], i);
  }
};

//月份下拉框绑定数据
Calendar.prototype.bindMonth = function() {
  var cm =document.getElementById("calendarMonth_z");
  cm.length = 0;
  for (var i = 0; i < 12; i++){
    cm.options[cm.length] = new Option(Calendar.language["months"][this.lang][i], i);
  }
};

//向前一月
Calendar.prototype.goPrevMonth = function(e){
  if (this.year == this.beginYear && this.month == 0){return;}
  this.month--;
  if (this.month == -1) {
    this.year--;
    this.month = 11;
  }
  this.date = new Date(this.year, this.month, 1);
  this.changeSelect();
  this.bindData();
};

//向后一月
Calendar.prototype.goNextMonth = function(e){
  if (this.year == this.endYear && this.month == 11){return;}
  this.month++;
  if (this.month == 12) {
    this.year++;
    this.month = 0;
  }
  this.date = new Date(this.year, this.month, 1);
  this.changeSelect();
  this.bindData();
};

//改变SELECT选中状态
Calendar.prototype.changeSelect = function() {
  var cy = document.getElementById("calendarYear_z"); 
  var cm = document.getElementById("calendarMonth_z"); 
  for (var i= 0; i < cy.length; i++){
    if (cy.options[i].value == this.date.getFullYear()){
      cy[i].selected = true;
      break;
    }
  }
  for (var i= 0; i < cm.length; i++){
    if (cm.options[i].value == this.date.getMonth()){
      cm[i].selected = true;
      break;
    }
  }
};

//更新年、月
Calendar.prototype.update = function (e){
  this.year  = document.getElementById("calendarYear_z").options[document.getElementById("calendarYear_z").selectedIndex].value;
  this.month = document.getElementById("calendarMonth_z").options[document.getElementById("calendarMonth_z").selectedIndex].value;
  this.date = new Date(this.year, this.month, 1);
  this.changeSelect();
  this.bindData();
};

//绑定数据到月视图
Calendar.prototype.bindData = function () {
  var calendar = this;
  var dateArray = this.getMonthViewArray(this.date.getFullYear(), this.date.getMonth());
  var tds = this.getElementById("calendarTable").getElementsByTagName("td");
  
  var currSelDate=null;
  if(this.dateControl!=null && this.dateControl.value.length>0)
  	currSelDate=new Date(parseInt(this.dateControl.value.substr(0,4),10),parseInt(this.dateControl.value.substr(6,2),10)-1,parseInt(this.dateControl.value.substr(8,2),10)).format(calendar.dateFormatStyle);
  
  for(var i = 0; i < tds.length; i++) {
  tds[i].style.backgroundColor = calendar.colors["td_bg_out"];
    tds[i].onclick = function () {return;};
    tds[i].onmouseover = function () {return;};
    tds[i].onmouseout = function () {return;};
    if (i > dateArray.length - 1) break;
    tds[i].innerHTML = dateArray[i];
    if (dateArray[i] != "&nbsp;"){
      tds[i].onclick = function () {
        if (calendar.dateControl != null){
          calendar.dateControl.value = new Date(calendar.date.getFullYear(),
                                                calendar.date.getMonth(),
                                                this.innerHTML).format(calendar.dateFormatStyle);
        }
        calendar.hide();
      };
      tds[i].onmouseover = function () {
        this.style.backgroundColor = calendar.colors["td_bg_over"];
      };
      tds[i].onmouseout = function () {
        this.style.backgroundColor = calendar.colors["td_bg_out"];
      };
	  tLoopDate=new Date(calendar.date.getFullYear(),calendar.date.getMonth(),
                   dateArray[i]).format(calendar.dateFormatStyle);
      if (new Date().format(calendar.dateFormatStyle) == tLoopDate
		  || currSelDate==tLoopDate) {
		if(currSelDate==tLoopDate){ //当前选中日期
		    tds[i].style.backgroundColor = calendar.colors["curSel_bg"];
            tds[i].onmouseout = function () {
          		this.style.backgroundColor = calendar.colors["curSel_bg"];
        	};
		}else{//当前日期
		    tds[i].style.backgroundColor = calendar.colors["cur_bg"];
            tds[i].onmouseout = function () {
          		this.style.backgroundColor = calendar.colors["cur_bg"];
        	};
		}
		tds[i].onmouseover = function () {
          this.style.backgroundColor = calendar.colors["td_bg_over"];
        };
      }//end if
    }
  }
};

//根据年、月得到月视图数据(数组形式)
Calendar.prototype.getMonthViewArray = function (y, m) {
  var mvArray = [];
  var dayOfFirstDay = new Date(y, m, 1).getDay();
  var daysOfMonth = new Date(y, m + 1, 0).getDate();
  for (var i = 0; i < 42; i++) {
    mvArray[i] = "&nbsp;";
  }
  for (var i = 0; i < daysOfMonth; i++){
    mvArray[i + dayOfFirstDay] = i + 1;
  }
  return mvArray;
};

//扩展 document.getElementById(id) 多浏览器兼容性 from meizz tree source
Calendar.prototype.getElementById = function(id){
  if (typeof(id) != "string" || id == "") return null;
  if (document.getElementById) return document.getElementById(id);
  if (document.all) return document.all(id);
  try {return eval(id);} catch(e){ return null;}
};

//扩展 object.getElementsByTagName(tagName)
Calendar.prototype.getElementsByTagName = function(object, tagName){
  if (document.getElementsByTagName) return document.getElementsByTagName(tagName);
  if (document.all) return document.all.tags(tagName);
};

//取得HTML控件绝对位置
Calendar.prototype.getAbsPoint = function (e){
  var x = e.offsetLeft;
  var y = e.offsetTop;
  while(e = e.offsetParent){
    x += e.offsetLeft;
    y += e.offsetTop;
  }
  if((x+210)>screen.width)
  	x=screen.width-210;
  
  return {"x": x, "y": y};
};

//显示日历
Calendar.prototype.show = function (dateControl, popControl) {
  if (dateControl == null){
    throw new Error("arguments[0] is necessary");
  }
  this.dateControl = dateControl;
  if (this.dateControl.value.length > 0){
  	this.date = new Date(parseInt(dateControl.value.substr(0,4),10),parseInt(dateControl.value.substr(6,2),10)-1,parseInt(dateControl.value.substr(8,2),10));
  	this.year = this.date.getFullYear();
  	this.month = this.date.getMonth();
    this.changeSelect();
    this.bindData();
  }
  if (popControl == null){
    popControl = dateControl;
  }
  var xy = this.getAbsPoint(popControl);
  this.panel.style.left = xy.x + "px";
  this.panel.style.top = (xy.y + dateControl.offsetHeight+3) + "px";
  this.panel.style.visibility = "visible";
  DivSetVisible(true);
};

//隐藏日历
Calendar.prototype.hide = function() {
  this.panel.style.visibility = "hidden";
  DivSetVisible(false);
};

//判断指定控件在x,y,x1,y1范围内
function inLoaction(obj,x,y,xlen,ylen){
	//只需要判断小区域的4个点是否在在大区域内
	if(x!=null&&y!=null&&xlen!=null&&ylen!=null){
		xlen=parseInt((xlen+"").replace("px",""),10);
		ylen=parseInt((ylen+"").replace("px",""),10);
		x=parseInt((x+"").replace("px",""),10);
		y=parseInt((y+"").replace("px",""),10);
		var xs=new Array();
		xs[0]=obj.offsetLeft;
		xs[1]=obj.offsetWidth+obj.offsetLeft;
		xs[2]=obj.offsetLeft;
		xs[3]=obj.offsetWidth+obj.offsetLeft;
		var ys=new Array();
		ys[0]=obj.offsetTop;
		ys[1]=obj.offsetTop;
		ys[2]=obj.offsetTop-obj.offsetHeight;
		ys[3]=obj.offsetTop-obj.offsetHeight; 
		for(var i=0;i<4;i++){
			if(xs[i]>=x&&xs[i]<=x+xlen&&ys[i]>=y&&ys[i]<=y+ylen)
				return true;
		}
	}
	return false;
}

//用于辅助日历不被select挡住
function DivSetVisible(state)
{
var DivRef = document.getElementById('calendarPanel');
var IfrRef = document.getElementById('calendarPanelDiv');
if(state)
{
	DivRef.style.display = "block";
	IfrRef.style.width = DivRef.offsetWidth;
	IfrRef.style.height = DivRef.offsetHeight;
	IfrRef.style.top = DivRef.style.top;
	IfrRef.style.left = DivRef.style.left;
	IfrRef.style.zIndex = DivRef.style.zIndex - 1;
	IfrRef.style.display = "block";
}
else
{
	DivRef.style.display = "none";
	IfrRef.style.display = "none";
}
}

//初始化
document.write('<div id="calendarPanel" style="position: absolute;visibility: hidden;z-index: 9999;background-color: #FFFFFF;border: 1px solid #cccccc;width:175px;font-size:12px; "></div><iframe id="calendarPanelDiv" src="javascript:false;" scrolling="no" frameborder="0" style="position:absolute; top:0px; left:0px; display:none;"></iframe>');


var calendar=null;
var currOutObj = null;
document.onclick=function(evt){
	if(calendar==null)return;
	if(evt==null)evt=window.event;
  	with(evt){
		set= evt.srcElement? evt.srcElement : evt.target; 
		if (set != currOutObj){
			while(set!=null){
				if(set.id=="calendarPanel")return;
				set=set.offsetParent;
			} 
			calendar.hide();
		}
  	}
};


//调用方法：
// dateControl 被设置参数的控件
// popControl 日历被显示位置的参考控件
function setday(dateControl, popControl){
	currOutObj=dateControl;
	calendar=new Calendar();
	calendar.show(dateControl, popControl);
}

