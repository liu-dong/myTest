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
  		<s:form action="findDayAction" method="post">
		    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
		    	<tr>
		    		<td width="30%">
		    			<s:text name="增加日程"></s:text>
		    		</td>
		    		<td width="30%">
		    			<s:a href="http://localhost:8080/StrutsDome/dataTimeManager/lookDay.jsp">查看日程</s:a>
		    		</td>
		    		<td width="40%">
		    			<s:text name="日程时间"></s:text>
		    			20<input type="text" size="1" name="year">年
		    			<input type="text" size="1" name="month">月
		    			<input type="text" size="1" name="day">日
		    			<input type="submit" value="修删日程">
		    		</td>
		    	</tr>
		   	</table>
		</s:form>
	</s:div>
	<hr noshade>
	<s:form action="addDayAction" method="post">
		<table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
			<tr>
    			<td height="30" width="50%" align="right">日程时间</td>
    			<td width="50%">
	    			20<input type="text" size="1" name="year">年
	    			<input type="text" size="1" name="month">月
	    			<input type="text" size="1" name="day">日
	    		</td>
			</tr>
			<tr>
    			<td height="30" width="50%" align="right">日程内容</td>
    			<td width="50%">
	    			<input type="text" size="30" name="thing">年
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
