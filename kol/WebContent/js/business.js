
/**
 * 查看发言的所有评论
 */
function allCommentsAbout(testimonialsId){
	var newInput = $("<input type='hidden' name='testimonialsId' value='"+testimonialsId+"'>");
	$("#channelForm").append(newInput);
	formTo("channelForm", "/comments/queryComments.do");
}

function formTo(formId, href){
	$("#"+formId).prop("action", href);
	$("#"+formId).submit();	
}

function turnToTuCaoBa(){
	formTo("channelForm", "/channel/tucaoba.do");
}
function turnToOther(){
	formTo("channelForm", "/channel/other.do");
}
function turnToCareer(){
	formTo("channelForm", "/channel/career.do");
}
function turnToEmotion(){
	formTo("channelForm", "/channel/emotion.do");
}
function turnToLife(){
	formTo("channelForm", "/channel/life.do");
}
function turnToIndex(){
	formTo("channelForm", "/channel/home.do");
}
function turnTo(href){
	formTo("channelForm", href);
}
/**
 * 顶一次
 * @param id
 */
function topOnce(id, changeAim) {
	var idtemp = id;
	//alert(id);
	$.ajax({
		url:"/commemorate/topOnce.do",
		cache:true,
		data:{
			id: idtemp
		},
		dataType:"text",
		success: function(data){
			if("success" == data){
				var pagetopTimes = $("#"+changeAim).text();
				if(!!! pagetopTimes) {
					pagetopTimes = 0;
				}
				pagetopTimes = pagetopTimes*1 + 1;
				$("#"+changeAim).text(pagetopTimes);
			}
		},
		error: function(){
			alert("顶失败了，没顶上去！重新试一下吧");
		}
		
	});
}