package service;

import dao.Dao;
import dao.UserDao;
import pojos.User;

public class UserService {

	static Dao<User, Integer> userDao = new UserDao();

	private User loggedIn = null;

	public int createUser(User obj) {
		int x = 0;
		if (userDao.findByUserName(obj.getUserName()) != null) {
			x = 1;
		}
		if (userDao.save(obj).getId() > 0) {
			userDao.save(obj);
			x = 2;
		}
		return x;
	}

	public User loginSuccess(String userName) {
		return userDao.findByUserName(userName);
	}

	public void setLoggedIn(User u) {
		loggedIn = u;
	}

	public User getLoggedUser () {
		User u = null;
		if (loggedIn != null) {
			u = loggedIn;
		}
		return u;
	}

	public int checkUserExists(String u, String p) {
		int x = 0;
		User user = userDao.findByUserName(u);
		if (user != null && user.getPassword().equals(p)) {
			x = 2;
		} else if (user != null) {
			x = 1;
		}
		return x;
	}
	
}
