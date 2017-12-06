package edu.login.Action;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport implements ServletRequestAware {

	private String userName;
	private String password1;
	private String password2;
	private String name;
	private String sex;
	private String birth;
	private String nation;
	private String edu;
	private String work;
	private String phone;
	private String place;
	private String email;
	private ResultSet rs=null;
	private String message="error";
	private HttpServletRequest request;

	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getNation() {
		return nation;
	}


	public void setNation(String nation) {
		this.nation = nation;
	}


	public String getEdu() {
		return edu;
	}


	public void setEdu(String edu) {
		this.edu = edu;
	}


	public String getWork() {
		return work;
	}


	public void setWork(String work) {
		this.work = work;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	//��֤ע����Ϣ�Ƿ�������ȷ
	public void validate(){
		if(getUserName()==null || getUserName().length()==0){
			addFieldError("userName", "��¼���ֲ�����Ϊ�գ�");
		}
		else{
			try{
				DB mysql=new DB();
				rs=mysql.selectMess(request, this.getUserName());
				if(rs.next()){
					addFieldError("userName", "�˵�¼�����Ѵ��ڣ�");
				}
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		if(getPassword1()==null || getPassword1().length()==0){
			addFieldError("password1", "�����¼������Ϊ�գ�");
		}
		if(getPassword2()==getPassword1() || getPassword2().length()==0){
			addFieldError("password2", "�ظ����벻����Ϊ�գ�");
		}
		if(!(getPassword1().equals(getPassword2()))){
			addFieldError("password2", "�������벻һ��");
		}
		if(getName()==null || getName().length()==0){
			addFieldError("name", "�û���������Ϊ�գ�");
		}
		if(getBirth().length()==0 || getBirth()==null || getBirth().equals("yyyy-mm-dd")){
			addFieldError("birth", "�û����ղ���Ϊ�գ�");
		}
		else{
			if(getBirth().length()!=10){
				addFieldError("birth", "�û����ո�ʽΪ'yyyy-MM-dd'!");
			}
			else{
				String an=this.getBirth().substring(4,5);
				String bn=this.getBirth().substring(7, 8);
				if(!(an.equals("-")) || !(bn.equals("-"))){
					addFieldError("birth", "�û����ո�ʽΪ'yyyy-MM-dd'!");
				}
			}
		}
		if(getNation()==null || getNation().length()==0){
			addFieldError("nation", "�û����岻��Ϊ�գ�");
		}
		if(getEdu().equals("1")){
			addFieldError("edu", "��ѡ���û�ѧ����");
		}
		if(getWork().equals("1")){
			addFieldError("work", "��ѡ���û�������");
		}
		if(getPhone()==null || getPhone().length()==0){
			addFieldError("phone", "�û��绰������Ϊ�գ�");
		}
		if(getPlace()==null || getPlace().length()==0){
			addFieldError("place", "�û���ַ����Ϊ�գ�");
		}
		if(getEmail()==null || getEmail().length()==0){
			addFieldError("email", "�û����䲻��Ϊ�գ�");
		}	
	}

	//
	public String execute() throws Exception{
		
		DB mysql=new DB();
		String mess=mysql.insertMess(request,this.getUserName(),
										this.getPassword1(),this.getName(),
										this.getSex(),this.getBirth(),
										this.getNation(),this.getEdu(),
										this.getWork(),this.getPhone(),
										this.getPlace(),this.getEmail());
		if(mess.equals("ok")){
			message=SUCCESS;
		}
		else if(mess.equals("one")){
			message=INPUT;
		}
		return message;
	}

	
}
