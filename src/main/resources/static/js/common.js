

function security_check(){
	var currentPath=window.location.pathname;
	console.log(currentPath);
	if(currentPath.indexOf("login.html")>0){
		return;
	}
	var username=sessionStorage.getItem("username");
    if(null==username||""==username){
	$.cookie('token',"");
	window.location.href="login.html";
}
}


//安全校验
//security_check();


/**
 *公用js 
 **/
//从cookie中读取信息
function get_cookie_userinfo(){
	//获取用户名
	var username= $.cookie('username');
	//获取用户唯一标识id
	var uid=$.cookie('uid');
    //获取用户头像 url
	var userimg = $.cookie('userimg');
	
	var userinfo={'username':username,'uid':uid,'userimg':userimg};
	
	return userinfo;
}
//设置cookie信息
function set_cookie_userinfo(res){
	var date = new Date();
    date.setTime(date.getTime()+24*3600*1000);//只能这么写，10表示10秒钟 设置cookie过期时间1天
	$.cookie('token',res.token.value,{path:"/",expires: date});
	//本地存储设置用户名
	sessionStorage.setItem("username",res.uname); 
	//存储本地用户头像
	sessionStorage.setItem("img",res.img); 
	sessionStorage.setItem("uid",res.uid); 
	
	
}

//验证用户是否登录
function check_cookie_userinfo(){
	var userinfo= get_cookie_userinfo();
	var tmp= userinfo.uid;
	if (typeof(tmp) == "undefined"){  
     return false;
    }
    if (tmp==-1){  
      return false;
    }else{
    	return true;
    }
}
//切换顶层登录状态条
function change_loginToolbar_status(){
	if(check_cookie_userinfo()){
		//已登录
		var userinfo=get_cookie_userinfo();
		$("#ulogin_status").hide();
		$("#l_username").text(userinfo.username);
		$("#l_userimg").attr("src",userinfo.userimg);
		$("#login_status").show();
	}else{
		//未登录
		$("#ulogin_status").show();
		$("#login_status").hide();
	}
}
//用户退出登录
function logout(){
	$.cookie('username',null,{path:"/"});
	$.cookie('uid',-1,{path:"/"});
	$.cookie('userimg',null,{path:"/"});
	//清空本地缓存
	localStorage.clear();
	
	//向服务端发送退出登录请求
	$.get("/login/logout",function(res){
			window.location.href="http://schoolnote.bigdata8.xin/schoolnote/html/case/case.html";
	});
	
	//window.reload();

}

//转化时间
function formatMsgTime (timespan) {
  var dateTime = new Date(timespan);
  var year = dateTime.getFullYear();
  var month = dateTime.getMonth() + 1;
  var day = dateTime.getDate();
  var hour = dateTime.getHours();
  var minute = dateTime.getMinutes();
  var second = dateTime.getSeconds();
  var now = new Date();
  var now_new = Date.parse(now.toDateString());  //typescript转换写法
  var milliseconds = 0;
  var timeSpanStr;
  milliseconds = now_new - timespan;
  if (milliseconds <= 1000 * 60 * 1) {
    timeSpanStr = '刚刚';
  }
  else if (1000 * 60 * 1 < milliseconds && milliseconds <= 1000 * 60 * 60) {
    timeSpanStr = Math.round((milliseconds / (1000 * 60))) + '分钟前';
  }
  else if (1000 * 60 * 60 * 1 < milliseconds && milliseconds <= 1000 * 60 * 60 * 24) {
    timeSpanStr = Math.round(milliseconds / (1000 * 60 * 60)) + '小时前';
  }
  else if (1000 * 60 * 60 * 24 < milliseconds && milliseconds <= 1000 * 60 * 60 * 24 * 15) {
    timeSpanStr = Math.round(milliseconds / (1000 * 60 * 60 * 24)) + '天前';
  }
  else if (milliseconds > 1000 * 60 * 60 * 24 * 15 && year == now.getFullYear()) {
    timeSpanStr = month + '-' + day + ' ' + hour + ':' + minute;
  } else {
    timeSpanStr = year + '-' + month + '-' + day + ' ' + hour + ':' + minute;
  }
  return timeSpanStr;
}

function parseStr2Date(datastr){
	return new Date(Date.parse(datastr.replace(/-/g, "/")));
}
//获取url最后的的名字
function getUrlSuffix(url1){
    var url= url1.split("/");
	return url[url.length-1].substr(0,url[url.length-1].lastIndexOf("?"));
}
//监测是否登录，未登录则跳转登录页面
function checklogin2redirect(urlsuffix){
	if(!check_cookie_userinfo()){
		//若未登录
	     var check_list=new Array("home.html","add.html","set.html","message.html")
	     if(!contains(check_list,urlsuffix)){
		 window.location.href="/schoolnote/html/user/login.html";
	  }
	}

}
//数组内是否包含该元素
function contains(arr, obj) {  
    var i = arr.length;  
    while (i--) {  
        if (arr[i] === obj) {  
            return true;  
        }  
    }  
    return false;  
}  

　 //获取浏览器参数a和b
function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
//随机字符串
function randomString(len) {
　　len = len || 32;
　　var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
　　var maxPos = $chars.length;
　　var pwd = '';
　　for (i = 0; i < len; i++) {
　　　　pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
　　}
　　return pwd;
}

function getFileSuffix(upFileName){
var index1=upFileName.lastIndexOf(".");
var index2=upFileName.length;
var suffix=upFileName.substring(index1+1,index2);//后缀名
return suffix;
}

/**************************************时间格式化处理************************************/
function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}


function ajax(url,data,callback){
		     $.ajax({
        	type:"post",
        	url:url,
        	async:true,
        	data:data,
        	processData:false,
        	contentType: "application/json",
        	success:function(res){
            if(res.status=="SUCCESS"){
            	if(typeof callback=='function'){
            		callback(1);
            	}
            	//top.layer.close(index);
			   // top.layer.msg("文章添加成功！");
 			    //layer.closeAll("iframe");
	 		    //刷新父页面
	 		    //parent.location.reload();
  	       	}else{
  	       		if(typeof callback=='function'){
            		callback(0);
            	}
  	       		//top.layer.close(index);
  	       		//layer.msg("文章添加失败",{icon:2});
  	       	}
        	},
        	error:function(res){
        		if(typeof callback=='function'){
            		callback(-1);
            	}
        		//top.layer.close(index);
        		//layer.msg("文章添加失败",{icon:2});
        	}
        });
}
