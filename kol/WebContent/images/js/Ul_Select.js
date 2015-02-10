// JavaScript Document

$(document).ready(function(){
/*****init******/
    var d=new Date();
	$("#showMonth").html(d.getFullYear());
    if(d.getFullYear()==d.getFullYear()){
                for(var i=0;i<=11;i++){
				    if(i<=d.getMonth())
                        $($("#month").children()[i]).addClass("active");
					else
					    $($("#month").children()[i]).removeClass("active");
			    }
			}else{
                for(var i=0;i<=11;i++){
                   $( $("#month").children()[i]).addClass("active")
			    }
			}

	$('.son_ul').hide(); //初始ul隐藏
	$('.select_box span').click(function(){ 
	   
	   $(this).parent().find('ul.son_ul').slideToggle();  //找到ul.son_ul显示
	   $(this).parent().find('li').hover(function(){$(this).addClass('select_hover')},function(){$(this).removeClass('select_hover')}); //li的hover效果

	});

	$('ul.son_ul li a').click(function(){
		$(this).parents('li').find('span').html($(this).html());
		$(this).parents('li').find('ul').slideUp();
	});

	$("#preb").click(function(){
		var y=parseInt($("#showMonth").html())-1;
		$("#showMonth").html(y);
		var d=new Date();
		if(y<=d.getFullYear()){
			if(y==d.getFullYear()){
                for(var i=0;i<=11;i++){
				    if(i<=d.getMonth())
                        $($("#month").children()[i]).addClass("active");
					else
					    $($("#month").children()[i]).removeClass("active");
			    }
			}else{
                for(var i=0;i<=11;i++){
                   $( $("#month").children()[i]).addClass("active")
			    }
			}
		}
	})

	$("#nexb").click(function(){
		var y=parseInt($("#showMonth").html())+1;
		$("#showMonth").html(y);
		var d=new Date();
        if(y<=d.getFullYear()){
			if(y==d.getFullYear()){
               for(var i=0;i<=11;i++){
				    if(i<=d.getMonth())
                        $($("#month").children()[i]).addClass("active");
					else
					    $($("#month").children()[i]).removeClass("active");
			   }
			}else{
                for(var i=0;i<=11;i++){
                   $( $("#month").children()[i]).addClass("active")
			    }
			}
		}else{
            for(var i=0;i<=11;i++){
               $( $("#month").children()[i]).removeClass("active")
			}
		}
	})

	$("#month label").click(function(){
		if($(this)[0].className.indexOf("active")<0) return;
	    $(".select_box span").html($("#showMonth").html()+"年"+$(this).html())
		$(".son_ul").slideUp();
	})

	$(document).click(function(e){
	    var evt = e || window.event;
		if(evt.target.id=="preb" || evt.target.id=="nexb") return;
		
        $('ul.son_ul').each(function(){
        	if($(this).css("display")=="none") return;
        	$(this).hide();
        })
	})
	//$('ul.son_ul li').click(function(){
	//$(this).parents('li').find('ul').slideUp();
	//});
	//$('body').mousedown(function(){
	//$('ul.son_ul').slideUp();
	//});
});