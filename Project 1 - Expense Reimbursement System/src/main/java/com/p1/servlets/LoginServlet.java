package com.p1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.pojos.Users;
import com.p1.service.UsersService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(LoginServlet.class);

	static UsersService uService = new UsersService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("partials/login.html")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.trace("in login Servlet");
		ObjectMapper mapper = new ObjectMapper();
		Users u = mapper.readValue(req.getInputStream(), Users.class);
		u = uService.checkUser(u.getUsername(), u.getPassword());
		HttpSession session = req.getSession();
		session.setAttribute("user", u);
		logger.trace("u: " + u);
		if (u.getRole_id() == 1) {
			resp.sendRedirect("partials/emp.html");
		} else if (u.getRole_id() == 2){
			resp.sendRedirect("partials/man.html");
		}
	}
	
}
