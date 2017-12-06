<%@ page import="JavaBean.MyFriBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title><s:text name="个人信息管理系统"></s:text></title>
    
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  </head>
  
  <body bgcolor="gray">
  	<hr noshade/>
  	<s:div align="center">
  		<s:form action="findFriAction" method="post">
		    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
		    	<tr>
		    		<td width="33%">
		    			<s:a href="http://localhost:8080/StrutsDome/friendManager/addFriend.jsp">增加联系人</s:a>
		    		</td>
		    		<td width="33%">
		    			<s:a href="http://localhost:8080/StrutsDome/friendManager/lookFriend.jsp">查看联系人</s:a>
		    		</td>
		    		<td width="33%">
		    			<s:text name="删除联系人"></s:text>
		    			<input type="text" name="friendname">
		    			<input type="submit" value="查找">
		    		</td>
		    	</tr>
		   	</table>
		</s:form>
	</s:div>
	<hr noshade>
	<s:form action="upFriAction" method="post">
    <table border="2" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
    	<%
    		/*
    			通过DB类中的方法，把登录用户的信息保存到session对象中，
    			在该页面中获取保存在session对象中的个人用户信息，并把用户信息输出到页面上
    		*/
    		ArrayList delemess = (ArrayList)session.getAttribute("findFriend");
    		if(delemess==null || delemess.size()==0){
    		%>
    		<s:div align="center"><%="您还没有添加联系人!" %></s:div>
    		<%
    		}
    		else{
    			for(int i=delemess.size()-1;i>=0;i--){
    				MyFriBean ff=(MyFriBean)delemess.get(i);
    				%>
    				
   				<tr>
   					<td>
   						<s:text name="用户姓名"></s:text>
   					</td>
   					<td>
   						<input type="text" name="name" values="<%=ff.getName() %>">
   					</td>
   				</tr>
   				<tr>
   					<td>
   						<s:text name="用户电话"></s:text>
   					</td>
   					<td>
   						<input type="text" name="phone" values="<%=ff.getPhone() %>">
   					</td>
   				</tr>
   				<tr>
   					<td>
   						<s:text name="用户邮箱"></s:text>
   					</td>
   					<td>
   						<input type="text" name="email" values="<%=ff.getEmail() %>">
  						</td>
   				</tr>
   				<tr>
   					<td>
   						<s:text name="工作单位"></s:text>
   					</td>
   					<td>
   						<input type="text" name="workPlace" values="<%=ff.getWorkPlace() %>">
  						</td>
   				</tr>    				
   				<tr>
   					<td>
   						<s:text name="家庭住址"></s:text>
   					</td>
   					<td>
   						<input type="text" name="place" values="<%=ff.getPlace() %>">
  						</td>
   				</tr>
   				<tr>
   					<td>
   						<s:text name="用户QQ"></s:text>
   					</td>
   					<td>
   						<input type="text" name="QQ" values="<%=ff.getQQ() %>">
  						</td>
   				</tr>    				
   				<tr>
   					<td colspan="2" align="center">
   						<input type="submit" value="确定" size="12">
   						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   						<input type="reset" value="清除" size="12">
   					</td>
   				</tr>
    				
    				<%
    			}
    		}
    	 %>
    </table>
    </s:form>
  </body>
</html>
