package edu.dateTimeManager.Action;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class UpdateDayAction extends ActionSupport implements
		ServletRequestAware {

	private String year;
	private String month;
	private String day;
	private String thing;
	private String date;
	private String userName;
	private String Day;
	private ResultSet rs=null;
	private String message="error";
	private HttpServletRequest request;
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public String getTime(){
		String time="";
		SimpleDateFormat ff=new SimpleDateFormat("yyyy-MM-dd");
		Date d=new Date();
		time=ff.format(d);
		return time;
	}
	
	private void message(String msg) {
		// TODO Auto-generated method stub
		int type=JOptionPane.YES_NO_OPTION;
		String title="��Ϣ��ʾ";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}

	@Override
	public void setServletRequest(HttpServletRequest hsr) {
		// TODO Auto-generated method stub
		request=hsr;
	}
	
	public void  validate(){
		
		String mess="";
		boolean DD=false;
		String time=getTime();
		StringTokenizer token=new StringTokenizer(time,"-");
		if(this.getYear()==null ||this.getYear().length()==0){
			mess=mess+"*���";
			addFieldError("year", "��ݲ�����Ϊ�գ�");
		}
		else if(Integer.parseInt("20"+this.getYear())<Integer.parseInt(token.nextToken()) || this.getYear().length()!=2){
			DD=true;
			addFieldError("year", "����ȷ��д��ݣ�");
		}
		if(this.getMonth()==null || this.getMonth().length()==0){
			mess=mess+"*�·�";
			addFieldError("month", "�·ݲ�����Ϊ�գ�");
		}
		else if(this.getMonth().length()>2 || Integer.parseInt(this.getMonth())<0 || Integer.parseInt(this.getMonth())>12){
			DD=true;
			addFieldError("month", "����ȷ��д�·ݣ�");
		}
		if(this.getDay()==null || this.getDay().length()==0){
			mess=mess+"*����";
			addFieldError("day", "���ڲ�����Ϊ�գ�");
		}
		else if(this.getDay().length()>2 || Integer.parseInt(this.getDay())<0 || Integer.parseInt(this.getDay())>31){
			DD=true;
			addFieldError("day", "����ȷ��д�ճ̣�");
		}
		if(this.getThing()==null || this.getThing().length()==0){
			mess=mess+"*�ճ̰���";
			addFieldError("thing", "�ճ̰��Ų�����Ϊ�գ�");
		}
		if(!mess.equals("")){
			mess=mess+"������Ϊ�գ�";
			message(mess);
		}
		if(DD){
			message("��д���ճ���Ч��");
		}
	}
	
	public String execute() throws Exception{
		
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		Day=mysql.returnDay(request);
		date="20"+this.getYear()+"-"+this.getMonth()+"-"+this.getDay();
		String D=mysql.updateDay(request, userName, Day, date, thing);
		
		if(D.equals("ok")){
			message=SUCCESS;
		}
		else if(D.equals("one")){
			message=INPUT;
		}
		return message;
	}

}
