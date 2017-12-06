<%@ page import="JavaBean.MyMessBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<html>
  <head>
  
    <title><s:text name="个人信息管理系统"></s:text></title>
    
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  </head>
  
  <body bgcolor="gray">
    <hr noshade/>
    <s:div align="center">
    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
    	<tr>
    		<td width="33%">
    			<s:text name="修改个人信息"></s:text>
    		</td>
    		<td width="33%">
    			<s:a href="http://localhost:8080/StrutsDome/personMessage/lookMessage.jsp">查看个人信息</s:a>
    		</td>
    		<td width="33%">
    			<s:a href="http://localhost:8080/StrutsDome/personMessage/updatePass.jsp">修改个人密码</s:a>
    		</td>
    	</tr>
    </table>
    </s:div>
    <hr noshade/>
    <s:form action="upMessAction" method="post">
    <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
    	<%
    		/*
    			通过DB类中的myMessage（）方法，把登录用户的信息保存到session对象中，
    			在该页面中获取保存在session对象中的个人用户信息，并把用户信息输出到页面上
    		*/
    		ArrayList MyMessage = (ArrayList)session.getAttribute("MyMess");
    		if(MyMessage==null || MyMessage.size()==0){
    		
    			response.sendRedirect("http://localhost:8080/StrutsDome/login/index.jsp");
    		}
    		else{
    			
    			for(int i=MyMessage.size()-1;i>=0;i--){
    				MyMessBean mess = (MyMessBean)MyMessage.get(i);
    				%>
    				
    				<tr>
    					<td height="30">
    						<s:text name="用户姓名"></s:text>
    					</td>
    					<td>
    						<input type="text" name="name" values="<%=mess.getName() %>">
    					</td>
    				</tr>
    				<tr>
    					<td height="30">
    						<s:text name="用户性别"></s:text>
    					</td>
    					<td>
    						<input type="text" name="sex" values="<%=mess.getSex() %>">
    					</td>
    				</tr>
    				<tr>
    					<td height="30">
    						<s:text name="出生日期"></s:text>
    					</td>
    					<td>
    						<input type="text" name="birth" values="<%=mess.getBirth() %>">
    					</td>
    				</tr>
    				<tr>
    					<td height="30">
    						<s:text name="用户民族"></s:text>
    					</td>
    					<td>
    						<input type="text" name="nation" values="<%=mess.getNation() %>">
   						</td>
    				</tr>
    				<tr>
    					<td height="30">
    						<s:text name="用户学历"></s:text>
    					</td>
    					<td>
    						<input type="text" name="edu" values="<%=mess.getEdu() %>">
   						</td>
    				</tr>
    				<tr>
    					<td height="30">
    						<s:text name="用户职称"></s:text>
    					</td>
    					<td>
    						<input type="text" name="work" values="<%=mess.getWork() %>">
   						</td>
    				</tr>
    				<tr>
    					<td height="30">
    						<s:text name="用户电话"></s:text>
    					</td>
    					<td>
    						<input type="text" name="phone" values="<%=mess.getPhone() %>">
   						</td>
    				</tr>
    				<tr>
    					<td height="30">
    						<s:text name="用户住址"></s:text>
    					</td>
    					<td>
    						<input type="text" name="place" values="<%=mess.getPlace() %>">
   						</td>
    				</tr>
    				<tr>
    					<td height="30">
    						<s:text name="用户邮箱"></s:text>
    					</td>
    					<td>
    						<input type="text" name="email" values="<%=mess.getEmail() %>">
   						</td>
    				</tr>
    				<tr>
    					<td colspan="2" align="center">
    						<input type="submit" value="确定">
    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    						<input type="reset" value="还原">
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
