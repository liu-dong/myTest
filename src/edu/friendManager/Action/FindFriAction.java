package edu.friendManager.Action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class FindFriAction extends ActionSupport implements ServletRequestAware {

	private String friendname;
	private String userName;
	private ResultSet rs=null;
	private String message="error";
	private HttpServletRequest request;
	
	
	
	public String getFriendname() {
		return friendname;
	}



	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}


	@Override
	public void setServletRequest(HttpServletRequest hsr) {
		// TODO Auto-generated method stub
		request=hsr;
	}


	private void message(String msg) {
		// TODO Auto-generated method stub
		int type=JOptionPane.YES_NO_OPTION;
		String title="��Ϣ��ʾ";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}
	
	public void validate(){
//		if(getFriendname()==null)
//			System.out.println("����Ϊ��");
//		return;
		if(this.getFriendname().equals("") || this.getFriendname().length()==0){
			message("��ϵ����������Ϊ�գ�");
			addFieldError("friendname", "��ϵ����������Ϊ��!");
		}
		else{
			try {
				DB mysql=new DB();
				userName=mysql.returnLogin(request);
				rs=mysql.selectFri(request, userName, this.getFriendname());
				if(!rs.next()){
					message("��ϵ�����������ڣ�");
					addFieldError("friendname", "��ϵ������������!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String execute() throws Exception{
		
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		String mess=mysql.updatePass(request,userName, this.getFriendname());
		if(mess.equals("ok")){
			message=SUCCESS;
		}
		return message;
	}
}
