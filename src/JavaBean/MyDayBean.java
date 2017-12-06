package JavaBean;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public class MyDayBean {

	private String Day;
	private String thing;
	private ResultSet rs=null;
	private HttpServletRequest request;
	
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	public String getThing() {
		return thing;
	}
	public void setThing(String thing) {
		this.thing = thing;
	}
	public void setHttpServletRequest(HttpServletRequest hsr) {
		request=hsr;
	}
}
