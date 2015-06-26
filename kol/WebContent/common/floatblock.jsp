<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style type="text/css">
#floatBlock {
	position: fixed; z-index: 100; right:5px; top: 40%;
}
#prevTestimonialDiv, #nextTestimonialDiv, #totopTestimonialDiv, #toendTestimonialDiv {
	background-color: #6eb4d6;
	cursor: pointer;
}

#currentIndex {
	width: 15px;border:1px solid #ccc
}

.whiteline-50 {
	height: 50px; width: 100%;
}
.whiteline-10 {
	height: 10px; width: 100%;
}

#currentIndexDiv {
	display: none;
}
</style>
<div id="floatBlock">
	<div id="totopTestimonialDiv">回顶部</div>
	<div class="whiteline-10"></div>
	<div id="prevTestimonialDiv">上一个</div>
	<div class="whiteline-50"></div>
	<div id="currentIndexDiv">
		当前：
		<br>
		<input id="currentIndex" value="1">
	</div>
	<div id="nextTestimonialDiv">下一个</div>
	<div class="whiteline-10"></div>
	<div id="toendTestimonialDiv">到最后</div>
</div>