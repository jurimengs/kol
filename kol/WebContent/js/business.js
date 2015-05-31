
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
function topOnce(id) {
	//alert(id);
	$.ajax({
		url:"/commemorate/topOnce.do",
		cache:true,
		data:{
			id:id
		},
		dataType:"json",
		success: function(data){
			
		},
		error: function(){}
		
	});
}