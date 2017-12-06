package JavaBean;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public class MyFileBean {

	private String title; 
	private String name;
	private String contentTyp;
	private String size;
	private String filePath;
	private ResultSet rs=null;
	private HttpServletRequest request;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentTyp() {
		return contentTyp;
	}

	public void setContentTyp(String contentTyp) {
		this.contentTyp = contentTyp;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setHttpServletRequest(HttpServletRequest hsr) {
		request=hsr;
	}
}
