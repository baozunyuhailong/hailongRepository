package com.spring.project.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
/**
 * 设置cookie
 * @param response 
 * @param key
 * @param value
 * @param maxAge
 */
	public static void setCookie(HttpServletResponse response, String key, String value, int maxAge) {
		Cookie cookie = new Cookie(key,value);
		if(maxAge>=0) {
			// 该Cookie失效的时间，单位秒。如果为正数，则该Cookie在maxAge秒之后失效。
			// 如果为负数，该Cookie为临时Cookie，关闭浏览器即失效，浏览器也不会以任何形式保存该Cookie。
			// 如果为0，表示删除该Cookie。默认为–1
			// 要想修改Cookie只能使用一个同名的Cookie来覆盖原来的Cookie，达到修改的目的。删除时只需要把maxAge修改为0即可。
			cookie.setMaxAge(maxAge);
		} 
		cookie.setPath("/");
		response.addCookie(cookie);
	}

    /**
     * 根据名字获取cookie
     * 
     * @param request
     * @param name
     *            cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     * 
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("a:");
	}
}
