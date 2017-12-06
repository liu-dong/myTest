package edu.friendManager.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteFriAction extends ActionSupport implements
		ServletRequestAware {

	private String message="error";
	private String name;
	private String userName;
	private HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest hsr) {
		// TODO Auto-generated method stub
		request=hsr;
	}

	public String execute() throws Exception{
		
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		String del=mysql.deleteFri(request, userName, name);
		if(del.equals("ok")){
			message=SUCCESS;
		}
		return message;
	}
}
