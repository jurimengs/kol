/*
 * psa client using AJAX XMLHttpRequest.
 *
 * DESCRIPTION
 * 
 *    psa offline helper function
 *
 * since: psa-offline.js,v 1.0 2013/08/14 13:44:08  
 *         
 */

//0.PSA OFFLINE FrameWork define
var PSA_Offline = {
	
	VERSION:'1.0',//版本
	
	DEBUG:false,   //是否debug  
	
	
	/******************************* PSA Offline Function *******************************/
	
	//Get CardInfos Include InnerCard And ExternalCard
	
	/**
	 * 
	 * 系统读卡
	 * 
	 * @param {Object}   witch 内卡或外卡
	 * @param {Object}   which out:减 in:加
	 * @param {function} cb 回调显示函数
	 * 
	 */
	_readCardInfos:function(witch,which,cb){
		if( ! isIe()){
			return createNoReadCardInfo();
		}
		
		if(!! document.getElementById("message_div")){
			document.getElementById("message_div").style.display="block";
			document.getElementById("message_loading_tip").innerHTML="正在读卡,请稍候";
		}
	    var ID = "";
	    var cardTypeRadios = document.getElementsByName(witch);
	    for (var i = 0; i < cardTypeRadios.length; i++) {
		   if(cardTypeRadios[i].checked){
			   ID =  cardTypeRadios[i].id;
		   }
	    }
	    
	    if(PSA_Offline.DEBUG){
	    	alert("choose ID:" + ID);	
	    }
	    
	    if(typeof ID == 'string' && ID != "" ){
	    	var cardType = document.getElementById(ID).value;
	    	if(PSA_Offline.DEBUG){
	    		alert("choose cardType:" + cardType);
	    	}

	    	//1.读卡信息
	    	if(PSA._readCardInfo(cardType,which)){
	    		//2.校验卡信息
	    		PSA._checkCard(which,cb);
	    	} else {
	    		document.getElementById("message_div").style.display="none";
				document.getElementById("message_loading_tip").innerHTML="正在读卡,请稍候";
	    	}
	    }
	},
	
	/**
	 * 简单读卡
	 * 
	 * @param  {Object} witch 内卡或外卡
	 * @return {cardNo} 
	 */
	_readCardNo:function(witch){
		var cardNo = "";
		var ID = "";
	    var cardTypeRadios = document.getElementsByName(witch);
	    for (var i = 0; i < cardTypeRadios.length; i++) {
		   if(cardTypeRadios[i].checked){
			   ID =  cardTypeRadios[i].id;
		   }
	    }
	    
	    if(PSA_Offline.DEBUG){
	    	alert("choose ID:" + ID);	
	    }
	    
	    if(typeof ID == 'string' && ID != "" ){
	    	var cardType = document.getElementById(ID).value;
	    	if(PSA_Offline.DEBUG){
	    		alert("choose cardType:" + cardType);
	    	}
	    	//1.读卡信息
	    	cardNo = PSA._readCardNo(cardType);
	    }
	    return cardNo;
	},

	/**
	 * 
	 * @param {Object} which  which out:减 in:加
	 * @param {Object} dmt
	 * @param {Object} so 
	 * @param {Object} sp
	 * @param {Object} cb
	 * @param {Object} m
	 * 
	 */
    _offlinePay:function(which,dmt,so,sp,cb,m){
		if(typeof m == 'string'){
			transType = m;
		}
		if(PSA._checkDebit('_offlinePay',which,dmt)){
			PSA._debitDevice(which,dmt,so,sp,cb,transType);
		}else{
			PSA._end_pay();
		}
	},
	
	/**
	 * 
	 * @param {Object} which
	 * @param {Object} so
	 * @param {Object} cb
	 * @param {Object} m
	 */
	_offlinePayException:function(which,so,cb,m){
		if(typeof m == 'string'){
			transType = m;
		}
		PSA._debitExceptionProcess(which,so,cb,transType);
	},
	
	/**
	 * 
	 * @param {Object} which  which out:减 in:加
	 * @param {Object} dmt
	 * @param {Object} so 
	 * @param {Object} sp
	 * @param {Object} pf
	 * @param {Object} pp
	 * @param {Object} cb
	 * @param {Object} m
	 * 
	 */
    _offlineReCharge:function(which,dmt,so,sp,pf,pp,cb,m){
    	if(typeof m == 'string'){
			transType = m;
		}
		if(PSA._checkDebit('_offlineReCharge',which,dmt)){
			PSA._quanCunDevice(which, dmt, so, sp,pf,pp,cb,transType);
		}else{
			PSA._end_pay();
		}
    },
	
    /**
     * 
     * @param {Object} which  which out:减 in:加
     * @param {Object} at
     * @param {Object} an
     * @param {Object} pn
     * @param {Object} da
     * @param {Object} tp
     * @param {Object} mo
     * @param {Object} cb 
     * 
     */
    _offlineReChargeExceptionProcess:function(which,at,an,pn,da,tp,mo,cb){
		PSA._quanCunDeviceExceptionProcess(which, at, an, pn,da,tp,mo,cb);
    }
	
};

function hideMsgBlock(){
	if(!! document.getElementById("message_div")){
		document.getElementById("message_div").style.display="none";
		document.getElementById("message_loading_tip").innerHTML="控件加载中,请稍候...";
	}
}
