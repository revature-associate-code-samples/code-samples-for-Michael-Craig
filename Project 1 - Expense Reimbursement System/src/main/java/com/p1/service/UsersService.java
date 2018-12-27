package com.p1.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.p1.dao.UsersDao;
import com.p1.pojos.Users;
import com.p1.servlets.LoginServlet;

public class UsersService {
	
	private static Logger log = Logger.getLogger(UsersService.class);

	static UsersDao uDao = new UsersDao();

	public List<Users> getAllUsers() {
		List<Users> u = uDao.findAll();
		if (u.size() == 0)
			return null;
		return u;
	}
	
	public Users checkUser(String user, String pass) {
		List<Users> u = uDao.findAll();
		boolean bool = false;
		for(Users usr : u) {
			if (usr.getUsername().equals(user) && usr.getPassword().equals(pass)) {
				log.trace(usr);
				bool = true;
				return usr;
			}
		}
		if (bool) {
			return null;			
		}
		return null;
	}

}
