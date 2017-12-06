package edu.friendManager.Action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class UpdateFriAction extends ActionSupport implements
		ServletRequestAware {

	private String name;
	private String phone;
	private String email;
	private String workPlace;
	private String place;
	private String QQ;
	private String message="error";
	private HttpServletRequest request;
	private ResultSet rs=null;
	private String userName;
	private String friendname;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	@Override
	public void setServletRequest(HttpServletRequest hsr) {
		// TODO Auto-generated method stub
		request=hsr;
	}
	
	public void  validate(){
		if(getName()==null ||getName().length()==0){
			addFieldError("name", "�û���������Ϊ��");
		}
		if(getPhone()==null || getPhone().length()==0){
			addFieldError("phone", "�û��绰������Ϊ�գ�");
		}
		if(getEmail()==null || getEmail().length()==0){
			addFieldError("email", "�û����䲻��Ϊ�գ�");
		}
		if(getWorkPlace()==null || getWorkPlace().length()==0){
			addFieldError("workPlace", "������λ����Ϊ�գ�");
		}
		if(getPlace()==null || getPlace().length()==0){
			addFieldError("place", "�û���ַ����Ϊ�գ�");
		}
		if(getQQ()==null || getQQ().length()==0){
			addFieldError("QQ", "�û���ַ����Ϊ�գ�");
		}
	}
	
	public String execute(){
		
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		friendname=mysql.returnFri(request);
		String fri=mysql.updateFri(request, userName, friendname, this.getName(), 
									this.getPhone(), this.getEmail(), 
									this.getWorkPlace(), this.getPlace(), 
									this.getQQ());
		
		if(fri.equals("ok")){
			message=SUCCESS;
		}
		return message;
	}

}
