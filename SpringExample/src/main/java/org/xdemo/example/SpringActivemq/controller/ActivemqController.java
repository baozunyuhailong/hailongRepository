package org.xdemo.example.SpringActivemq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xdemo.example.SpringActivemq.model.User;
import org.xdemo.example.SpringActivemq.service.DemoService;
import org.xdemo.example.SpringActivemq.utils.CookieUtils;

import com.alibaba.fastjson.JSON;

 
/**
 * @作者 hailong
 * @日期 2017-2-21
 * @描述 ajax（jsonp）跨域调用测试
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {
     
    //@Resource 
	@Autowired
    DemoService demoService;
     
    /**
     * 发送消息到队列
     * @param message
     * @return String
     */
    @RequestMapping("queueSender")
    @ResponseBody
    public String queueSender(@RequestParam("message")String message, HttpSession session, 
    		HttpServletRequest request, HttpServletResponse response){
    	
//    	HttpSession session2 = request.getSession();
//    	session2.setAttribute("username", message);
//    	
//    	return "username=" + message;

    	Enumeration<String> esl = session.getAttributeNames();
    	
    	while(esl.hasMoreElements())  
        {
    		System.out.println(esl.nextElement());  
        }

    	session.setAttribute("test_code",123);
    	session.setAttribute("test_code2","测试");
    	//String code = (String)session.getAttribute("identify_code");
        String opt="";
        try {
        	opt = demoService.sayHello(message);
        } catch (Exception e) {
            opt=e.getCause().toString();
        }
        return opt;
    }
    
    /**
     * 发送json
     * @param message
     * @return String
     */
    @RequestMapping("sendJson")
    @ResponseBody
    public User sendJson(@RequestBody User user, HttpSession session, HttpServletRequest request){

        String opt="";
        try {
        	opt = demoService.sayHello(user.getMessage());
        } catch (Exception e) {
            opt=e.getCause().toString();
        }
        User userR = new User();
        userR.setMessage(opt);
        return userR;
    }
 
    /**
     * 发送jsonp
     * @param message
     * @return String
     */
    @RequestMapping(value = "sendJsonp.json", method = RequestMethod.GET)
    public void sendJsonp(String message, HttpSession session, HttpServletRequest request, HttpServletResponse response){

    	Map map = new HashMap<>();
        if ("0".equals(message)){
            CookieUtils.setCookie(response, "number", message, 0);
            map.put("number", "删除cookie,key=number");
        }else{
            CookieUtils.setCookie(response, "number", message, -1);
            map.put("number", "使用默认cookie,key=number,value="+ message);
        }

    	response.setContentType("text/plain");  
        String callbackFunName =request.getParameter("callbackparam");//得到js函数名称  
        try {
        	response.getWriter().write(callbackFunName + "(" + JSON.toJSONString(map) + ")");
        	//response.getWriter().write(callbackFunName + "([ { name:\"John\"}])"); //返回jsonp数据  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }

    /**
     * 查询jsonp
     * @param message
     * @return String
     */
    @RequestMapping(value = "findJsonp.json", method = RequestMethod.GET)
    public void findJsonp(HttpSession session, HttpServletRequest request, HttpServletResponse response){

    	Cookie cookie = CookieUtils.getCookieByName(request, "number");

    	response.setContentType("text/plain");
        String callbackFunName = request.getParameter("callbackparam");//得到js函数名称  
        try {
        	response.getWriter().write(callbackFunName + "(" + JSON.toJSONString(cookie) + ")");

        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }

    /**
     * 发送jsonp
     * @param message
     * @return String
     */
    @RequestMapping(value = "/sendGetJSON.json")
    public void sendGetJSON(HttpServletRequest request,HttpServletResponse response,Model model, String message){

    	Map map = new HashMap<>();
        if ("0".equals(message)){
            CookieUtils.setCookie(response, "number", message, 0);
            map.put("number", "删除cookie,key=number");
        }else{
            CookieUtils.setCookie(response, "number", message, -1);
            map.put("number", "使用默认cookie,key=number,value="+ message);
        }
        String contentType = "application/json";
        response.setContentType(contentType);

        try{
            String jsoncallback = request.getParameter("jsoncallback");
            String json = jsoncallback + "(" + JSON.toJSONString(map) + ");";
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        }catch (IOException e){
        	
        }
    }
}