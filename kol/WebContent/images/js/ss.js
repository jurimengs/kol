var ss_kks = [];
function gen_key(index,alg){
	var chars = ['0','1','2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f','g','h','i','j','k','l','m',
		'n','o','p','q','r','s','t','u','v','w','s','y','z'];
	var cur="" ;
	if(alg == "01"){
		for(var i =0;i<8;i++){
		   var id = Math.ceil(Math.random()*35);
           cur += chars[id];	
		}
		
	}else if(alg == "02"){
		
	}
	ss_kks[index+alg] = cur;
}

function sp_trs_fro(index,value,alg){
	 gen_key(index,alg);
	 return CryptoJS.DES.encrypt(CryptoJS.MD5(value),ss_kks[index+alg])+"";
}

function sp_trs_to(index,encryptvalue,alg){
	 return CryptoJS.DES.decrypt(encryptvalue, ss_kks[index+alg])+"";
}