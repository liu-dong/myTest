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
	<table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
		<tr>
			<td>
				<th height="30">好友姓名</th>
				<th height="30">好友电话</th>
				<th height="30">好友邮箱</th>
				<th height="30">工作单位</th>
				<th height="30">家庭地址</th>
				<th height="30">用户QQ</th>
				<th height="30">用户操作</th>
			</td>
		</tr>
		<%
			ArrayList friends=(ArrayList)session.getAttribute("findfriend");
			if(friends==null || friends.size()==0){
		 %>
		 <s:div align="center"><%="您还没有添加联系人！" %></s:div>
		 <%
		 	}
		 	else{
		 		for(int i=friends.size()-1;i>=0;i--){
		 			MyFriBean ff=(MyFriBean)friends.get(i);
		 			%>
		 			<tr>
		 				<td><%=ff.getName() %></td>
		 				<td><%=ff.getPhone() %></td>
		 				<td><%=ff.getPlace() %></td>
		 				<td><%=ff.getEmail() %></td>
		 				<td><%=ff.getWorkPlace() %></td>
		 				<td><%=ff.getQQ() %></td>
		 				<td>
		 					<s:a href="http://localhost:8080/StrutsDome/friendManager/updateFriend.jsp">修改</s:a>
		 					<s:a href="deleteFriAction">删除</s:a>
		 				</td>
		 			</tr>
		 			<%
		 		}
		 	}
		  %>
	</table>
  </body>
</html>
