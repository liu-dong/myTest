package DBJavaBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.struts2.views.velocity.components.SelectDirective;

import JavaBean.MyDayBean;
import JavaBean.MyFileBean;
import JavaBean.MyFriBean;
import JavaBean.MyMessBean;
import JavaBean.UserNameBean;

public class DB {

	private String driverName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/personmessage?useUnicode=true&amp;characteEncoding=UTF-8";
	private String user = "root";
	private String password = "548464";
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	private HttpServletRequest request;

	public DB() {

	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRequest(HttpServletRequest hsr) {
		request = hsr;
	}


	// 完成连接数据库操作，生成容器并返回
	public Statement getStatement() {
		try {
			//加载驱动
			Class.forName(getDriverName());
			//获得数据连接
			con = DriverManager.getConnection(getUrl(), getUser(),
					getPassword());
			return con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 完成注册，把用户的注册信息录入到数据库中
	public String insertMess(HttpServletRequest request, String userName,
			String password1, String name, String sex, String birth,
			String nation, String edu, String work, String phone, String place,
			String email) {
		// TODO Auto-generated method stub
		try {
			String sure = null;
			rs = selectMess(request, userName);
			// 判断用户名是否已经存在，如果存在返回One
			if (rs.next()) {
				sure = "one";
			} else {
				String sql = "insert into user"
						+"(userName,password,name,sex,birth,nation,edu,work, phone,place,email)"
						+ "values("+"'"+userName+"'"+","+""+"'"+password+ "'"+ ","+ "'"+ name+ "'"+ ","
						+ "'"+ sex+ "'"+ ","+ "'"+ birth+ "'"+ ","+ "'"+ nation+ "'"+ ","+ "'"+ edu+ "'"+ ","
						+ "'"+ work+ "'"+ ","+ ""+ "'"+ phone+ "'"+ ","+ "'"+ place+ "'"+ ","+ "'" + email + "'" + ")";
				st = getStatement();
				int row = st.executeUpdate(sql);
				if (row == 1) {
					// 调用myMessage()方法，更新session中保存的用户信息
					String mess = myMessage(request, userName);
					if (mess.equals("ok")) {
						sure = "ok";
					} 
					else {
						sure = null;
					}
				} 
				else {
					sure = null;
				}
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	// 更新注册的个人信息
	public String updateMess(HttpServletRequest request, String userName,
			String name, String sex, String birth, String nation, String edu,
			String work, String phone, String place, String email) {
		
		try {
			String sure = null;
			String sql = "update user set name= '"+name+"', sex='"+sex+"', birth='"+birth+"', "
												+ "nation='"+nation+"', edu='"+edu+"', work='"+work+"', "
												+ "phone='"+phone+"', place='"+place+"', email='"+email+"' "
														+ "where userName='"+userName+"'";
			st = getStatement();
			int row = st.executeUpdate(sql);
			if (row == 1) {
				// 调用，myMessage方法，更新session中保存的用户信息
				String mess = myMessage(request, userName);
				if (mess.equals("ok")) {
					sure = "ok";
				} 
				else {
					sure = null;
				}
			} 
			else {
				sure = null;
			}
			return sure;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//查询个人信息，并返回结果集rs
	public ResultSet selectMess(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		try{     
				String sql = "select * from user where userName='" + userName + "'";
				st = getStatement();
				return st.executeQuery(sql);   
		}
		catch (Exception e){    
			e.printStackTrace();
			return null;
		}
	}
	
	//把个人信息通过myMessBean保存到session对象中
	private String myMessage(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		try{
			ArrayList listName=null;
			HttpSession session=request.getSession();
			listName = new ArrayList();
			rs=selectMess(request,userName);
			while(rs.next()){
				MyMessBean mess=new MyMessBean();
				mess.setName(rs.getString("name"));
				mess.setSex(rs.getString("sex"));
				mess.setBirth(rs.getString("birth"));
				mess.setWork(rs.getString("work"));
				mess.setEdu(rs.getString("edu"));
				mess.setNation(rs.getString("nation"));
				mess.setPhone(rs.getString("phone"));
				mess.setPlace(rs.getString("place"));
				mess.setEmail(rs.getString("email"));
				listName.add(mess);
				session.setAttribute("MyMess", listName);
			}
			return "ok";
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
                      
	//修改密码
	public String updatePass(HttpServletRequest request, String userName,
			String password) {
		// TODO Auto-generated method stub
		
		try {
			String sure=null;
			String sql="update user set password='"+password+"' where userName='"+userName+"'";
			st=getStatement();;
			int row=st.executeUpdate(sql);
			if(row==1){
				
				String mess=myLogin(request, userName);
				if(mess.equals("ok")){
					sure="ok";
				}
				else{
					sure=null;
				}
			}
			else{
				sure=null;
			}
			return sure;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	//把登录用户的信息保存到session对象中
	private String myLogin(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		try{
			ArrayList listName=null;
			HttpSession session=request.getSession();
			listName = new ArrayList();
			rs=selectMess(request,userName);
			if(rs.next()){
				rs=selectMess(request, userName);
				while(rs.next()){
					UserNameBean mess=new UserNameBean();
					mess.setUserName(rs.getString("userName"));
					mess.setPassword(rs.getString("password"));
					listName.add(mess);
					session.setAttribute("userName", listName);
				}
			}
			else{
					session.setAttribute("userName", listName);					
			}	
			return "ok";
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//查询登录名和密码是否存在
	public ResultSet selectLogin(HttpServletRequest request, String userName, String password){
		try{
			String sql="select * from user where userName='"+userName+"' and password='"+password+"'";
			st=getStatement();
			return st.executeQuery(sql);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//返回登录用户的用户名
	public String returnLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String LoginName=null;
		HttpSession session = request.getSession();
		ArrayList login=(ArrayList) session.getAttribute("userName");
		if(login==null || login.size()==0){
			LoginName=null;
		}
		else{
			for(int i=login.size()-1;i>=0;i--){
				UserNameBean nm=(UserNameBean)login.get(i);
				LoginName=nm.getUserName();
			}
		}
		return LoginName;
	}
	
	
	
	
	//添加联系人
	public String insertFri(HttpServletRequest request, String userName, 
							String name, String phone, String email, 
							String workPlace, String place, String QQ){
		try {
			String sure=null;
			rs=selectFri(request,userName,name);
			//判断联系人姓名是否存在
			if(rs.next()){
				
				sure="one";
			}
			else{
				String sql="insert into friends"
						+ "(userName,name,phone,email,workPlace,place,QQ)"
						+ "values("+"'"+userName+"'"+","+"'"+name+"'"+","
						+"'"+phone+"'"+","+"'"+email+"'"+","+"'"+workPlace+"'"+","
						+"'"+place+"'"+","+"'"+QQ+"'"+")";
				st=getStatement();
				int row=st.executeUpdate(sql);
				if(row==1){
					//调用myFriends()方法，更新session中通讯录中的信息
					String fri=myFriend(request, userName);
					if(fri.equals("ok")){
						sure="ok";
					}
					else{
						sure=null;
					}
				}
				else{
					sure=null;					
				}
			}
			return sure;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//删除联系人
	public String deleteFri(HttpServletRequest request, String userName, String name){
		try {
			String sure=null;
			String sql="delete from friends where userName='"+userName+"' and name='"+name+"'";
			st=getStatement();
			int row=st.executeUpdate(sql);
			if(row==1){
				//调用myFriends()方法，更新session中通讯录中的信息
				String fri=myFriend(request, userName);
				if(fri.equals("ok")){
					sure="ok";
				}
				else{
					sure=null;
				}
			}
			else{
				sure=null;					
			}
			return sure;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	//修改联系人
	public String updateFri(HttpServletRequest request, String userName,
							String friendName, String name, String phone, 
							String email, String workPlace, String place, 
							String QQ){
		try {
			String sure=null;
			//先删除该联系人的信息
			String del=deleteFri(request, userName, friendName);
			if(del.equals("ok")){
				//重新录入修改后的信息
				String in=insertFri(request, userName, name, phone, email, workPlace, place, QQ);
				if(in.equals("ok")){
					//调用myFriends()方法，更新session中的通讯录信息
					String fri=myFriend(request, userName);
					if(fri.equals("ok")){
						sure="ok";
					}
					else{
						sure=null;
					}
				}
				else{
					sure=null;
				}
			}
			else{
				sure=null;
			}
			return sure;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//查询联系人
	public ResultSet selectFri(HttpServletRequest request, String userName,
			String name) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from friends where userName='"+userName+"' and name= '"+name+"' ";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	//获取通讯录中所有联系人的信息
	public ResultSet selectFriAll(HttpServletRequest request, String userName){
		try {
			String sql="select * from friends where userName='"+userName+"' ";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//获取通讯录中所有联系人的信息,并把它们保存到session对象中
	public String myFriend(HttpServletRequest request, String userName){
		try{
			ArrayList listName=null;
			HttpSession session=request.getSession();
			listName = new ArrayList();
			rs=selectFriAll(request, userName);
			if(rs.next()){
				rs=selectFriAll(request, userName);
				while(rs.next()){
					MyFriBean mess=new MyFriBean();
					mess.setName(rs.getString("name"));
					mess.setPhone(rs.getString("phone"));
					mess.setEmail(rs.getString("email"));
					mess.setWorkPlace(rs.getString("workPlace"));
					mess.setPlace(rs.getString("place"));
					mess.setQQ(rs.getString("QQ"));
					listName.add(mess);
					session.setAttribute("friends", listName);
				}
			}
			else{
					session.setAttribute("friends", listName);					
			}	
			return "ok";
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	//查找联系人，并将其信息保存到session对象中
	public String findFri(HttpServletRequest request, String userName, String name){
		
		try {
			ArrayList listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList();
			rs=selectFri(request, userName, name);
			if(rs.next()){
				rs=selectFri(request, userName, name);
				while(rs.next()){
					MyFriBean mess=new MyFriBean();
					mess.setName(rs.getString("name"));
					mess.setPhone(rs.getString("phone"));
					mess.setEmail(rs.getString("email"));
					mess.setWorkPlace(rs.getString("workPlace"));
					mess.setPlace(rs.getString("place"));
					listName.add(mess);
					session.setAttribute("findfriend", listName);
				}
			}
			else{
				session.setAttribute("findfriend", listName);
			}
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	//从查找到的联系人到session对象中获取联系人姓名，并返回
	public String returnFri(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String FriendName=null;
		HttpSession session=request.getSession();
		ArrayList login=(ArrayList)session.getAttribute("findfriend");
		if(login==null || login.size()==0){
			FriendName=null;
		}
		else{
			for(int i=login.size()-1;i>=0;i--){
				MyFriBean nm=(MyFriBean) login.get(i);
				FriendName=nm.getName();
			}
		}
		return FriendName;
	}
	
	
	
	
	//添加日程
	public String insertDay(HttpServletRequest request, String userName, 
							String date, String thing){
		
		try {
			String sure=null;
			rs=selectDay(request,userName,date);
			//判断日程是否有安排
			if(rs.next()){
				sure="one";
			}
			else{
				String sql="insert into date"
						+ "(userName,date,thing)"+" values("+"'"+userName+
						"'"+","+"'"+date+"'"+","+"'"+thing+"'"+")";
				st=getStatement();
				int row=st.executeUpdate(sql);
				if(row==1){
					//调用myDayTime()方法，更新session对象中的日程信息
					String day=myDayTime(request,userName);
					if(day.equals("ok")){
						sure="ok";
					}
					else{
						sure=null;
					}
				}
			}
			return sure;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//删除日程
	public String deleteDay(HttpServletRequest request, String userName,
			String date){
		try {
			String sure=null;
			String sql="delete from date where userName='"+userName+"' and date='"+date+"'";
			st=getStatement();
			int row=st.executeUpdate(sql);
			if(row==1){
				//调用myDayTime()方法，更新session中保存的日程的信息
				String day=myDayTime(request, userName);
				if(day.equals("ok")){
					sure="ok";
				}
				else{
					sure=null;
				}
			}
			else{
				sure=null;					
			}
			return sure;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//修改日程
	public String updateDay(HttpServletRequest request, String userName,
			String Day,String date, String thing){
		try {
			String sure=null;
			//先删除该日程
			String del=deleteDay(request, userName, Day);
			if(del.equals("ok")){
				//重新录入修改后的信息
				String in=insertDay(request, userName, date, thing);
				if(in.equals("ok")){
					//调用myDayTime()方法，更新session中的通讯录信息
					String day=myDayTime(request, userName);
					if(day.equals("ok")){
						sure="ok";
					}
					else{
						sure=null;
					}
				}
				else{
					sure=null;
				}
			}
			else{
				sure=null;
			}
			return sure;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//查询日程
	public ResultSet selectDay(HttpServletRequest request, String userName,
			String date) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from date where userName='"+userName+"' and date='"+date+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//查询所有日程信息
	public ResultSet selectDayAll(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from date where userName='"+userName+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//查询所有日程信息，并把它们保存到session对象中
	public String myDayTime(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		try{
			ArrayList listName=null;
			HttpSession session=request.getSession();
			listName = new ArrayList();
			rs=selectDayAll(request, userName);
			if(rs.next()){
				rs=selectDayAll(request, userName);
				while(rs.next()){
					MyDayBean mess=new MyDayBean();
					mess.setDay(rs.getString("date"));
					mess.setThing(rs.getString("thing"));
					listName.add(mess);
					session.setAttribute("day", listName);
				}
			}
			else{
					session.setAttribute("day", listName);					
			}	
			return "ok";
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//查找日程，并把日程信息保存到session对象中	
	public String findDay(HttpServletRequest request, String userName,
			String date) {
		// TODO Auto-generated method stub
		try {
			ArrayList listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList();
			rs=selectFri(request, userName, date);
			if(rs.next()){
				rs=selectDay(request, userName, date);
				while(rs.next()){
					MyDayBean mess=new MyDayBean();
					mess.setDay(rs.getString("date"));
					mess.setThing(rs.getString("thing"));
					listName.add(mess);
					session.setAttribute("findday", listName);
				}
			}
			else{
				session.setAttribute("findday", listName);
			}
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	//从查找到的日程session对象中获取日程信息，并返回
	public String returnDay(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String date=null;
		HttpSession session=request.getSession();
		ArrayList login=(ArrayList)session.getAttribute("findday");
		if(login==null || login.size()==0){
			date=null;
		}
		else{
			for(int i=login.size()-1;i>=0;i--){
				MyDayBean nm=(MyDayBean) login.get(i);
				date=nm.getDay();
			}
		}
		return date;
	}
	
	
	
	
	
	//保存上传文件的信息
	public String insertFile(HttpServletRequest request, String userName, String title, 
								String name, String contentType, String size, String filePath){
		
		try {
			String sure=null;
			rs=selectFile(request,userName,"title",name);
			//查询标题是否存在
			if(rs.next()){
				sure="title";
			}
			else{
				//查询文件名是否存在
				rs=selectFile(request, userName, "name", name);
				if(rs.next()){
					sure="name";
				}
				else{
					String sql="insert into file"
							+ "(userName,title,name,contentType,size,filePath) "
							+ "values("+"'"+userName+"'"+","+"'"+title+"'"+","+"'"+name+"'"
							+","+"'"+contentType+"'"+","+"'"+size+"'"+","+"'"+filePath+"'"+")";
					st=getStatement();
					int row=st.executeUpdate(sql);
					if(row==1){
						//调用myFile()方法，更新session中的保存的文件信息
						String file=myFile(request,userName);
						if(file.equals("ok")){
							sure="ok";
						}
						else{
							sure=null;
						}
					}
					else{
						sure=null;
					}
				}
			}
			return sure;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	//删除文件
	public String deleteFile(HttpServletRequest request, String userName,
			String title){
		try {
			String sure=null;
			String sql="delete from file where userName='"+userName+"' and title='"+title+"'";
			st=getStatement();
			int row=st.executeUpdate(sql);
			if(row==1){
				//调用myDayTime()方法，更新session中保存的日程的信息
				String file=myFile(request, userName);
				if(file.equals("ok")){
					sure="ok";
				}
				else{
					sure=null;
				}
			}
			else{
				sure=null;					
			}
			return sure;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//修改文件
	public String updateFile(HttpServletRequest request, String userName, String title, 
			String name, String contentType, String size, String filePath){
		try {
			String sure=null;
			//先删除该文件
			String del=deleteFile(request, userName, title);
			if(del.equals("ok")){
				//重新录入修改后的信息
				String in=insertFile(request, userName, title, name, contentType, size, filePath);
				if(in.equals("ok")){
					//调用myFile()方法，更新session中的保存的文件信息
					String file=myFile(request, userName);
					if(file.equals("ok")){
						sure="ok";
					}
					else{
						sure=null;
					}
				}
				else{
					sure=null;
				}
			}
			else{
				sure=null;
			}
			return sure;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//查询文件
	public ResultSet selectFile(HttpServletRequest request, String userName,
			 String type, String name) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from file where userName='"+userName+"' and "+type+"='"+name+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	//查询所有文件信息
	private ResultSet selectFileAll(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from file where userName='"+userName+"'";
			st=getStatement();
			return st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//查找所有文件信息，并把文件的信息保存到session对象中
	private String myFile(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		try{
			ArrayList listName=null;
			HttpSession session=request.getSession();
			listName = new ArrayList();
			rs=selectFileAll(request, userName);
			if(rs.next()){
				rs=selectFileAll(request, userName);
				while(rs.next()){
					MyFileBean mess=new MyFileBean();
					mess.setName(rs.getString("name"));
					mess.setTitle(rs.getString("title"));
					mess.setContentTyp(rs.getString("contentType"));
					mess.setSize(rs.getString("size"));
					mess.setFilePath(rs.getString("filePate"));
					listName.add(mess);
					session.setAttribute("file", listName);
				}
			}
			else{
					session.setAttribute("file", listName);					
			}	
			return "ok";
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//查找文件信息，并把文件的信息保存到session对象中
	public String findFile(HttpServletRequest request, String userName,
			String title) {
		// TODO Auto-generated method stub
		try {
			ArrayList listName=null;
			HttpSession session=request.getSession();
			listName=new ArrayList();
			rs=selectFile(request, userName, "title", title);
			if(rs.next()){
				rs=selectFile(request, userName, "title", title);
				while(rs.next()){
					MyFileBean mess=new MyFileBean();
					mess.setTitle(rs.getString("title"));
					mess.setName(rs.getString("name"));
					mess.setContentTyp(rs.getString("contentType"));
					mess.setSize(rs.getString("size"));
					mess.setFilePath(rs.getString("filePath"));
					listName.add(mess);
					session.setAttribute("findday", listName);
				}
			}
			else{
				session.setAttribute("findday", listName);
			}
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	//根据不同的条件，从查找到的文件session对象中获取相应的文件信息，并返回
	public String returnFile(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String file=null;
		HttpSession session=request.getSession();
		ArrayList login=(ArrayList)session.getAttribute("findFile");
		if(login==null || login.size()==0){
			file=null;
		}
		else{
			for(int i=login.size()-1;i>=0;i--){
				MyDayBean nm=(MyDayBean) login.get(i);
				file=nm.getDay();
			}
		}
		return file;
	}
	
	

	
	/*
	 * 调用myLogin()、myMessage()、myFriends()、myDayTime()、myFile()方法，把所有和用户有关的信息保存到session对象中。该方法在登录成功后调用
	 * */
	public String addList(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		String sure=null;
		String login=myLogin(request, userName);
		String mess=myMessage(request, userName);
		String fri=myFriend(request, userName);
		String day=myDayTime(request, userName);
		if(login.equals("ok") && mess.equals("ok") && fri.equals("ok") && day.equals("ok")){
			sure="ok";
		}
		else{
			sure=null;
		}
		return sure;
	}
	
	//一个带参数的信息提示框，供调试使用
	private void message(String msg) {
		// TODO Auto-generated method stub
		int type=JOptionPane.YES_NO_OPTION;
		String title="信息提示";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}



	

	
}
