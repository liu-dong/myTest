package edu.friendManager.Action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class AddFriAction extends ActionSupport implements ServletRequestAware {
	
	private String name;
	private String place;
	private String QQ;
	private String phone;
	private String workPlace;
	private String email;
	private ResultSet rs=null;
	private String message="error";
	private String userName=null;
	private HttpServletRequest request;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		else{
			try {
				
				DB mysql=new DB();
				userName=mysql.returnLogin(request);
				rs=mysql.selectFri(request, userName, this.getName());
				if(rs.next()){
					addFieldError("name", "���û��Ѵ��ڣ�");
				}
			} catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
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
	
	public String execute() throws Exception{
		
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		String fri=mysql.insertFri(request, userName, this.getName(), 
									this.getPhone(), this.getEmail(), 
									this.getWorkPlace(), this.getPlace(), 
									this.getQQ());
		
		if(fri.equals("ok")){
			message=SUCCESS;
		}
		else if(fri.equals("one")){
			message=INPUT;
		}
		return message;
	}

}
