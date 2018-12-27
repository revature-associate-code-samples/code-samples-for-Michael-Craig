package com.p1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LoadViewsServlet extends HttpServlet {

	private static Logger log = Logger.getLogger(LoadViewsServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String resourcePath = "partials/" + process(req, resp) + ".html";
		req.getRequestDispatcher(resourcePath).forward(req, resp);
	}

	static String process(HttpServletRequest req, HttpServletResponse resp) {
		log.info("LOAD VIEW REQUEST SENT TO: " + req.getRequestURI());
		switch (req.getRequestURI()) {
		case "/Project1/home.view":
			return "home";
		case "/Project1/login.view":
			return "login";
		case "/Project1/signUp.view":
			return "signUp";
		case "/Project1/loggedIn.view":
			return "loggedIn";
		case "/Project1/employee.view":
			return "employee";
		case "/Project1/createReim.view":
			return "createReim";
		case "/Project1/emp.view":
			return "emp";
		case "/Project1/man.view":
			return "man";
		}
		return null;
	}
	
}