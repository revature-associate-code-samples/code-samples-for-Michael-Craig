package service;

import dao.AccountTypeDao;
import dao.Dao;
import pojos.AccountType;

public class AccTypeService {

	static Dao<AccountType, Integer> accountTypeDao = new AccountTypeDao();

}
