package edu.login.Action;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware {

	private String userName;
	private String password;
	private ResultSet rs=null;
	private String message=ERROR;
	private HttpServletRequest request;
	


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setServletRequest(HttpServletRequest hsr) {
		// TODO Auto-generated method stub
		request=hsr;
	}

	public void validate(){
		if(this.getUserName()==null || this.getUserName().length()==0){
			addFieldError("username", "请输入登录名字！");
		}
		else{
			try{
				DB mysql=new DB();
				rs=mysql.selectMess(request,this.getUserName());
				if(!rs.next()){
					addFieldError("username", "此用户尚未注册！");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(this.getPassword()==null || this.getPassword().length()==0){
			addFieldError("password", "请输入登录密码！");
		}
		else{
			try{
				DB mysql=new DB();
				rs=mysql.selectMess(request,this.getUserName());
				if(rs.next()){
					rs=mysql.selectLogin(request, this.getUserName(), 
										this.getPassword());
					if(!rs.next()){
						addFieldError("password", "登录密码错误！");
					}
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public String execute() throws Exception{
		
		//实例化对数据库操作的封装类
		DB mysql = new DB();
		//调用DB类中的方法，实现登录有关的操作
		String add=mysql.addList(request,this.getUserName());
		if(add.equals("ok")){
			message=SUCCESS;
		}
		return message;
		
	}
}
