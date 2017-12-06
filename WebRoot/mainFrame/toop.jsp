<%@ page import="JavaBean.UserNameBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:text name="个人管理系统"></s:text></title>
  </head>
  
  <body bgcolor="#CCDDEE">
  	<%
  		String loginname=null;
  		/*
  			DB类中通过myLogin()方法把登录用户的信息保存到session对象中 ，在该页
  			面中获取保存在session对象中的用户登录名，并把登录名输出到该页面上
  		*/
  		ArrayList login=(ArrayList)session.getAttribute("userName");
  		if(login==null || login.size()==0){
  			
  			loginname="水木年华";
  		}
  		else{
  			for(int i=login.size()-1;i>=0;i--){
  				UserNameBean nm=(UserNameBean)login.get(i);
  				loginname=nm.getUserName();
  			}
  		}
  	 %>
  	<table width="100%" align="right" bgcolor="white">
  		<tr>
  			<td>
  				<a href="http://localhost:8080/StrutsDome/personMessage/lookMessage.jsp" target="main">个人信息管理</a>
  			</td>
  			<td>
  				<a href="http://localhost:8080/StrutsDome/friendManager/lookFriend.jsp" target="main">通讯录管理</a>
  			</td>
  			<td>
  				<a href="http://localhost:8080/StrutsDome/dataTimeManager/lookDay.jsp" target="main">日程安排管理</a>
  			</td>
  			<td>
  				<a href="http://localhost:8080/StrutsDome/fileManager/lookFile.jsp" target="main">个人文件管理</a>
  			</td>
  			<td>
  				<a href="http://localhost:8080/StrutsDome/login/index.jsp" target="_top">退出主页面</a>
  			</td>
  			<td>
  				欢迎<%=loginname %>使用本系统！
  			</td>
  		</tr>
  	</table>
  </body>
</html>
