package com.spring.project.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者 hailong
 * @日期 2017-2-25
 * @描述 session使用redis跨域共享
 */
@Controller
@RequestMapping("/sessonShare")
public class SessionShareController {

	/**
     * 设定session值
     * localhost:8082/SpringExample/sessonShare/set?message=3
     * 
     * @param message
     * @return String
     */
    @RequestMapping("set")
    @ResponseBody
    public String set(@RequestParam("message")String message, HttpSession session, 
    		HttpServletRequest request, HttpServletResponse response){

    	HttpSession session2 = request.getSession();
    	session2.setAttribute("username", message);

    	return "username=" + message;
    }

	/**
     * 取得session值
     * @param message
     * @return String
     */
    @RequestMapping("get")
    @ResponseBody
    public String get(HttpSession session, 
    		HttpServletRequest request, HttpServletResponse response){

    	HttpSession session2 = request.getSession();
    	return  session2.getAttribute("username") +"";
    }

	/**
     * 设定与取得session值
     * 
     * @param message
     * @return String
	 * @throws UnknownHostException 
     */
    @RequestMapping("gsSession")
    @ResponseBody
    public String gsSession(@RequestParam("message")String message, @RequestParam("action")String action, HttpSession session, 
    		HttpServletRequest request, HttpServletResponse response) throws UnknownHostException{

    	HttpSession session2 = request.getSession();
    	
    	String addr = InetAddress.getLocalHost().getHostAddress();
    	String hostName = InetAddress.getLocalHost().getHostName();
    	
    	String message2 = "addr:"+ addr +" hostName: " + hostName;
    	
    	if("set".equals(action)){
    		//session的name是username
        	session2.setAttribute("username", message);
        	System.out.println("set:" + session.getId());
    	} else if("get".equals(action)){
    		message2 =(String)session2.getAttribute("username");
    		System.out.println("get:" + session.getId());
    	}

    	return message2;
    }
}
