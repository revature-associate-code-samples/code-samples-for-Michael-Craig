package com.p1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.pojos.Reimbursement;
import com.p1.service.ReimbursementService;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(EmployeeServlet.class);

	static ReimbursementService rService = new ReimbursementService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.trace("in employee servlet");
		List<Reimbursement> rs = rService.getAllRmbs();
		HttpSession session = req.getSession();
		String[] s = session.getAttribute("user").toString().split(", ");
		logger.trace(s);
		String userId = null;
		String roleId = null;
		for (String x : s) {
			if (x.contains("users_id")) {
				userId = x.substring(16);
				logger.trace("set userId: " + userId);
			} else if (x.contains("role_id")) {
				roleId = x.substring(8, 9);
				logger.trace("set roleId: " + roleId);
			} 
		}
		if (roleId.equals("1") || roleId.equals("1]")) {
			for (int i = 0; i < rs.size(); i++) {
				if (!rs.get(i).toString().contains("author=" + userId)) {
					rs.remove(rs.get(i));
					i--;
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(rs);
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
		rService.updateStatus(r.getId(), r.getStatus_id(), timestamp);
	}
}
