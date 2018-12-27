package com.p1.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.pojos.Reimbursement;
import com.p1.pojos.Users;
import com.p1.service.ReimbursementService;

@WebServlet("/reimbursement")
public class ReimbursementServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(ReimbursementServlet.class);

	static ReimbursementService rService = new ReimbursementService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.trace(session.getAttribute("users_id"));
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
		logger.trace("xx" + r);
		Users u = (Users) session.getAttribute("user");
		logger.trace(u);
		r = rService.createReimbursement(r.getAmount(), timestamp, r.getResolved(), r.getDescription(),
				u.getUsers_id(), 1, 1, r.getType_id());
		logger.trace("ADDED NEW R " + r);
	}

}
