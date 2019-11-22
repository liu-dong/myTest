package com.dong.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------------doGet---------------");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("admin".equals(username) && "123456".equals(password)){
//            resp.sendRedirect(req.getContextPath()+"/success.jsp");
            req.getRequestDispatcher("success.jsp").forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath()+"/fail.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------------doPost---------------");
        doGet(req,resp);
    }

    /*@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------------Service---------------");
    }*/

    @Override
    public void destroy() {
        System.out.println("---------------注销Servlet---------------");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("---------------初始化Servlet---------------");
    }
}
