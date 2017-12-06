package edu.personManager.Action;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class UpdatePassAction extends ActionSupport implements ServletRequestAware {

	private String userName;
	private String password1;
	private String password2;
	private HttpServletRequest request;
	private String message=ERROR;
	
	
	
	public String getPassword1() {
		return password1;
	}



	public void setPassword1(String password1) {
		this.password1 = password1;
	}



	public String getPassword2() {
		return password2;
	}



	public void setPassword2(String password2) {
		this.password2 = password2;
	}



	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	
	private void message(String msg) {
		// TODO Auto-generated method stub
		int type=JOptionPane.YES_NO_OPTION;
		String title="��Ϣ��ʾ";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}
	
	public void validate(){
		
		if(!(password1.equals(password2))){
			message("�������벻ͬ��");
			addFieldError("password2", "�������벻ͬ��");
		}
	}




	public String execute() throws Exception{
		
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		String mess=mysql.updatePass(request,userName, this.getPassword1());
		if(mess.equals("ok")){
			message=SUCCESS;
		}
		return message;
	}
}
