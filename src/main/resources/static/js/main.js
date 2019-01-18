mui.init();
//检测是否为空
var num=1;//类名后缀
var isAward=4;//最低可抽奖的答对题数
var questionLen=$(".mui-table-view").length;//问题长度
var totalRight=0;//答对问题总个数
$(function(){

//首页立即登录
$('.content_button').click(function(){
	$(".huodong").hide();//活动首页隐藏
	$(".login_main").addClass("fall");//下落特效
	$(".login_liu").addClass('fei');//流星
});

//提交点击事件
$(".tijiao").click(function(){
	var radioLen=$("input[type='radio']:checked").length;//单选选中个数
	var checkboxLen=$("input[type='checkbox']:checked").length;//复选选中个数
	var chooseNum=getVal().join('');//选中的答案数组转化为字符串
	//	alert(chooseNum);

//判断提交是否为空
	if(radioLen>0 || checkboxLen>0){
		$(".question"+num).find(".answer").css({"display":"block"});//显示正确答案
		$("#my_answer"+num).text(chooseNum);//显示选中的答案

//		$("#right_answer"+num).text();//显示正确答案

		panduan();//判断是否与答案一致，一致则正确
		//如果num大于等于问题的长度就判断是否可以抽奖
		if(num>=questionLen){
//			alert("可以抽奖了");
//			alert(isAward);
			if(totalRight>=isAward){
				$(".tijiao").css({"display":"none"});
				$("#tip").html("亲,您答对"+"<span style='color:red;font-size:5vh;'>"+totalRight+"</span>"+"道题!"+"<br/>"+"正在去抽奖的路上.");
				mui('.mui-popover').popover('toggle',document.getElementById("openPopover"));
				//跳到抽奖页面
				setTimeout(function(){
					mui('.mui-popover').popover('hide',document.getElementById("openPopover"));//hide掉提示框
					$("#ml-main").css({"display":"block"});
				},2000);
			}else{
				$(".tijiao").remove();
				$("#tip").html("亲,您答对"+"<span style='color:red;font-size:5vh;'>"+totalRight+"</span>"+"道题!"+"<br/>"+"无缘抽奖啦.");
				mui('.mui-popover').popover('toggle',document.getElementById("openPopover"));
				//返回首页
				setTimeout(function(){window.location.href="index.html";},2000);
			}
		}

		$(".tijiao").attr("disabled","disabled");//提交按钮不可用
		//$('html,body').animate({scrollTop:$('.tijiao').offset().top}, 800);//平滑过渡到页面底部
		setTimeout(function(){
			$(".question"+num).remove();//移除上一道题
			$(".question"+(num+1)).css({"display":"block"});//显示下一道题
			$(".tijiao").attr("disabled",false);
			num++;//题数加一
		}
		,1500);

	}else{
		mui('.mui-popover').popover('toggle',document.getElementById("openPopover"));
	}

});

//确认按钮点击事件
$(".queren").click(function(){
	testNumber();
});
//取消按钮点击事件
$(".quxiao").click(function(){
	window.location.href="index.html";
//	//强行隐藏页面，造成关闭的假象
//	$(document.body).hide();
//	//微信下关闭页面
//	WeixinJSBridge.call('closeWindow');
//	window.close();
});

});
//手机号码验证函数
function testNumber(){
    var val = $("#number").val();
    var reg = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
    if(!reg.test(val)){
        $('.login_msg').html("手机号码错误");
        setTimeout(function(){$('.login_msg').html("");},1000);
    } else{
    	$(".login").hide();
    }
}
//找到每一个可见元素下面选中的input元素的值函数
function getVal(){
	var arr=[];
	$(".mui-table-view:visible").find('input:checked').each(function(index,element){
		arr.push($(element).val());
		arr.join('');
	});
	return arr;
	//arr.join("");//将数组转化成字符串
}

//判断对错函数
function panduan(){
	if($("#my_answer"+num).text()==$("#right_answer"+num).text()){
//		alert("true");
		$("#isRight"+num).addClass("mui-icon-checkmarkempty");//正确的图标
		totalRight++;//答对题数加一
	}else{
//		alert("false");
		$("#isRight"+num).addClass("mui-icon-close");//错误的图标
	}
}

//隐藏微信分享按钮
function onBridgeReady(){
	WeixinJSBridge.call('hideOptionMenu');//隐藏分享按钮，showOptionMenu显示
}

if (typeof WeixinJSBridge == "undefined"){
 if( document.addEventListener ){
     document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
 }else if (document.attachEvent){
     document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
     document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
 }
}else{
 	onBridgeReady();
}
