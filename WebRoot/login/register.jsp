<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><s:text name="个人信息管理系统->注册"/></title>
  </head> 
  
  <body>
    <s:form action="registerAction" method="post">
    	<table align="center">
    		<tr>
    			<td width="40%">
    				<table border="2" bgcolor="#AABBCCDD" width="100%" align="center">
    					<tr>
    						<td colspan="2" align="center">
    							<font color="yellow">
    								<s:text name="请填写以下注册信息"></s:text>
    							</font>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:textfield name="userName" label="登录名"></s:textfield>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:password name="password1" label="密码" size="21"/>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:password name="password2" label="再次输入密码" size="21"/>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:textfield name="name" label="用户真实姓名"></s:textfield>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:text name="用户性别:"></s:text>
    						</td>
							<td>
								<input type="radio" name="sex" value="男" checked/>男
    							<input type="radio" name="sex" value="女">女
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:textfield name="birth" label="出生日期"></s:textfield>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:textfield name="nation" label="用户民族"></s:textfield>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:select name="edu" label="用户学历" 
    							 headerValue="------请选择-----"
    							 headerKey="1" list="{'博士','硕士','本科','专科','高中','初中','小学','其他'}">
    							</s:select>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:select name="work" label="用户职称" 
    							 headerValue="------请选择-----"
    							 headerKey="1" list="{'软件测试工程师','软件开发工程师','教师','学生','职员','经理','公务员','其他'}">
    							</s:select>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:textfield name="phone" label="用户电话"></s:textfield>
    						</td>
    					</tr>
    					<tr>
							<td>
    							<s:textfield name="place" label="用户住址"></s:textfield>
    						</td>
    					</tr>
    					<tr>
    						<td>
    							<s:textfield name="email" label="用户邮箱"></s:textfield>
    						</td>
    					</tr>
    					<tr>
    						<td colspan="2" align="center">
    							<input type="submit" value="确定">
    							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    							<input type="reset" value="清空">
    							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    							<a href="http://localhost:8080//StrutsDome/login/index.jsp">返回</a>
    						</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	</table>
    </s:form>
  </body>
</html>