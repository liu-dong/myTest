package edu.dateTimeManager.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class FindDayAction extends ActionSupport implements ServletRequestAware {

	private String year;
	private String month;
	private String day;
	private String date;
	private String userName;
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

	public String getTime(){
		String time="";
		SimpleDateFormat ff=new SimpleDateFormat("yyyy-MM-dd");
		Date d=new Date();
		time=ff.format(d);
		return time;
	}
	
	private void message(String msg) {
		// TODO Auto-generated method stub
		int type=JOptionPane.YES_NO_CANCEL_OPTION;
		String title="信息提示";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}

	@Override
	public void setServletRequest(HttpServletRequest hsr) {
		// TODO Auto-generated method stub
		request=hsr;
	}
	
	public void  validate(){
		
		String mess="";
		boolean Y=true,M=true,D=true;
		boolean DD=false;
		String time=getTime();
		StringTokenizer token=new StringTokenizer(time,"-");
		if(this.getYear()==null ||this.getYear().length()==0){
			Y=false;
			mess=mess+"*年份";
			addFieldError("year", "年份不允许为空！");
		}
		else if(Integer.parseInt("20"+this.getYear())<Integer.parseInt(token.nextToken()) || this.getYear().length()!=2){
			DD=true;
			addFieldError("year", "请正确填写年份！");
		}
		if(this.getMonth()==null || this.getMonth().length()==0){
			M=false;
			mess=mess+"*月份";
			addFieldError("month", "月份不允许为空！");
		}
		else if(this.getMonth().length()>2 || Integer.parseInt(this.getMonth())<0 || Integer.parseInt(this.getMonth())>12){
			DD=true;
			addFieldError("month", "请正确填写月份！");
		}
		if(this.getDay()==null || this.getDay().length()==0){
			D=false;
			mess=mess+"*日期";
			addFieldError("day", "日期不允许为空！");
		}
		else if(this.getDay().length()>2 || Integer.parseInt(this.getDay())<0 || Integer.parseInt(this.getDay())>31){
			DD=true;
			addFieldError("day", "请正确填写日程！");
		}
		if(Y&&M&&D){
			try {
				DB mysql=new DB();
				userName=mysql.returnLogin(request);
				date="20"+this.getYear()+"-"+this.getMonth()+"-"+this.getDay();
				rs=mysql.selectFri(request, userName, date);
				if(!rs.next()){
					message("该日程暂无安排！");
					addFieldError("year", "该日程暂无安排！");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(!mess.equals("")){
			mess=mess+"不允许为空！";
			message(mess);
		}
		if(DD){
			message("填写的日程无效！");
		}
	}
	
	public String execute() throws Exception{
		
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		date="20"+this.getYear()+"-"+this.getMonth()+"-"+this.getDay();
		String dd=mysql.findDay(request, userName, date);
		
		if(dd.equals("ok")){
			message=SUCCESS;
		}
		else if(dd.equals("one")){
			message=INPUT;
		}
		return message;
	}

}
