<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    // String basePath = request.getScheme() + "://"
    //        + request.getServerName() + ":" + request.getServerPort()
    //        + path + "/";
    
    String basePath = request.getScheme() + "://www.hailong.com:8082"
            + path + "/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
 
<title>Test Demo程序</title>
 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="<%=basePath%>resources/common/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/index.css">
<script type="text/javascript">

	$("#queueSender").click(function(){
		alert("123");
	});

    function send(controller){
    	 debugger;
        if($("#message").val()==""){
            $("#message").css("border","1px solid red");
            return;
        }else{
            $("#message").css("border","1px solid gray");
        }
        $.ajax({
            type: 'post',
            url:'<%=basePath%>activemq/'+controller,
            dataType:'text', 
            data:{"message":$("#message").val()},
            success:function(data){
        		debugger;
                if(data=="suc"){
                    $("#status").html("<font color=green>发送成功</font>");
                    setTimeout(clear,1000);
                }else{
                    $("#status").html("<font color=red>"+data+"</font>");
                    setTimeout(clear,5000);
                }
            },
            error:function(data){
        		debugger;
                $("#status").html("<font color=red>ERROR:"+data["status"]+","+data["statusText"]+"</font>");
                setTimeout(clear,5000);
            }
             
        });
    }
    
    function sendJson(controller){

        if($("#message").val()==""){
            $("#message").css("border","1px solid red");
            return;
        }else{
            $("#message").css("border","1px solid gray");
        }
        var json = {
                "message": $("#message").val(),
        };
        $.ajax({
        	url:'<%=basePath%>activemq/'+controller,
            type: 'post',
            data: JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            dataType: 'json', 
            success:function(data){
        		debugger;
                if(data=="suc"){
                    $("#status").html("<font color=green>发送成功</font>");
                    setTimeout(clear,1000);
                }else{
                    $("#status").html("<font color=red>"+data+"</font>");
                    setTimeout(clear,5000);
                }
            },
            error:function(data){
        		debugger;
                $("#status").html("<font color=red>ERROR:"+data["status"]+","+data["statusText"]+"</font>");
                setTimeout(clear,5000);
            }
        });
    }

    function sendJsonp(controller){

		debugger;
		if($("#message").val()==""){
		    $("#message").css("border","1px solid red");
		    return;
		}else{
		    $("#message").css("border","1px solid gray");
		}
		$.ajax({
		    type: 'GET',
		    url:'<%=basePath%>activemq/'+controller,
		    data:{"message":$("#message").val()},
            async: false,
		    dataType: "jsonp",
		    //jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
            //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
            jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数   
            jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来   
		    success:function(data){

		    	 $("#status").html("<font color=red>"+ data.number +"</font>");
		    },
		    error:function(data){
		    	debugger;
		        $("#status").html("<font color=red>ERROR:"+data["status"]+","+data["statusText"]+"</font>");
		        setTimeout(clear,5000);
		    }
		});
    }
    
    function findJsonp(controller){

		$.ajax({
		    type: 'GET',
		    url:'<%=basePath%>activemq/'+controller,
		    //data:{"message":$("#message").val()},
            async: false,
		    dataType: "jsonp",
		    //jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
            //jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
            jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数   
            jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来   
		    success:function(data){

		    	 $("#status").html("<font color=red>" + data.name + ":" + data.value +"</font>");
		    },
		    error:function(data){
		    	debugger;
		        $("#status").html("<font color=red>ERROR:"+data["status"]+","+data["statusText"]+"</font>");
		        setTimeout(clear,5000);
		    }
		});
    }

    function sendGetJSON(controller){

		if($("#message").val()==""){
		    $("#message").css("border","1px solid red");
		    return;
		}else{
		    $("#message").css("border","1px solid gray");
		}
		var message = $("#message").val();
 		$.getJSON('<%=basePath%>activemq/' + controller + '?message=' + message  + '&jsoncallback=?', function(data){
 			$("#status").html("<font color=red>"+ data.number +"</font>");
		});
/* 		$.getJSON('activemq/sendGetJSON.json?type=1&jsoncallback=?', function(status){
			  alert("123");
		}); */
    }

    function clear(){
        $("#status").html("");
    }
 
</script>
</head>

<body>
    <h1>Test demo</h1>
    <div id="producer">
        <h2>Producer</h2>
        <textarea id="message"></textarea>
        <br>
        <div>
	        <button onclick="send('queueSender')">发送的Queue</button>
	        <button onclick="sendJson('sendJson')">发送Json</button>
        </div>
        <br>
        <div>
	        <button onclick="sendJsonp('sendJsonp.json')">发送Jsonp</button>
	        <button onclick="sendGetJSON('sendGetJSON.json')">发送GetJSON</button>
	        <button onclick="findJsonp('findJsonp.json')">查询Jsonp</button>
        </div>
        <br>
        <span id="status"></span>
        <br>
        <p>8082</p>
        <br>
        <button id ="queueSender">发送的Queue_IE</a>
        <br>
    </div>
</body>
</html>