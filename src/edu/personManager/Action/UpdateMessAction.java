package edu.personManager.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class UpdateMessAction extends ActionSupport implements ServletRequestAware{

	private String name;
	private String sex;
	private String birth;
	private String nation;
	private String edu;
	private String work;
	private String phone;
	private String place;
	private String email;
	
	private String userName;
	private HttpServletRequest request;
	private String message=ERROR;
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
	
	public void validate(){
		
		if(getName()==null || getName().length()==0){
			addFieldError("name", "�û���������Ϊ�գ�");
		}
		if(getSex()==null || getSex().length()==0){
			addFieldError("name", "�û���������Ϊ�գ�");
		}
		if(getBirth()==null || getBirth().length()==0 || getBirth().equals("yyyy-mm-dd")){
			addFieldError("birth", "�û����ղ���Ϊ�գ�");
		}
		else{
			if(getBirth().length()!=10){
				addFieldError("birth", "�û����ո�ʽΪ'yyyy-mm-dd'!");
			}
			else{
				String an=this.getBirth().substring(4,5);
				String bn=this.getBirth().substring(7, 8);
				if(!(an.equals("-")) || !(bn.equals("-"))){
					addFieldError("birth", "�û����ո�ʽΪ'yyyy-mm-dd'!");
				}
			}
		}
		if(getNation()==null || getNation().length()==0){
			addFieldError("nation", "�û����岻��Ϊ�գ�");
		}
		if(getEdu()==null || getEdu().length()==0){
			addFieldError("edu", "��ѡ���û�ѧ����");
		}
		if(getWork()==null || getWork().length()==0){
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
	
	public String execute() throws Exception{
		
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		String mess=mysql.updateMess(request,userName, this.getName(),
										this.getSex(),this.getBirth(),
										this.getNation(),this.getEdu(),
										this.getWork(),this.getPhone(),
										this.getPlace(),this.getEmail());
		if(mess.equals("ok")){
			message=SUCCESS;
		}
		return message;
	}
	
}
