package com.p1.servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.p1.service.UsersService;

@WebServlet("/home")
public class LoadHomeViewsServlet extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(LoadHomeViewsServlet.class);

	static UsersService uService = new UsersService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}	

}