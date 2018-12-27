package com.p1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.pojos.Reimbursement;
import com.p1.service.ReimbursementService;

@WebServlet("/loggedIn")
public class LoggedInServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(LoggedInServlet.class);

	static ReimbursementService rService = new ReimbursementService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.trace("in loggedIn servlet");
		List<Reimbursement> r = rService.getAllRmbs();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(r);
		logger.trace("FINDING ALL R. JSON: " + json);
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}
	
}
