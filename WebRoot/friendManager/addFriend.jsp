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
  		<s:form action="findFriAction" method="post">
		    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
		    	<tr>
		    		<td width="33%">
		    			<s:text name="增加联系人"></s:text>
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
	<s:form action="addFriAction" method="post">
		<table border="2" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
			<tr>
				<td>
					<s:textfield name="name" label="好友姓名"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					<s:textfield name="phone" label="好友电话"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					<s:textfield name="email" label="好友邮箱"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					<s:textfield name="workPlace" label="工作单位"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					<s:textfield name="place" label="家庭住址"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
					<s:textfield name="QQ" label="QQ"></s:textfield>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
						<input type="submit" value="确定" size="12">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="清除" size="12">
				</td>
			</tr>
		</table>
	</s:form>
  </body>
</html>
