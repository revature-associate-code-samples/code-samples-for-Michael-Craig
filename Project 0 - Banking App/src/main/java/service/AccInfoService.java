package service;

import java.util.List;

import dao.AccountInfoDao;
import pojos.AccountInfo;

public class AccInfoService {

	static AccountInfoDao accountInfoDao = new AccountInfoDao();

	public List<AccountInfo> getAccounts() {
		return accountInfoDao.findAll();
	}

	public List<AccountInfo> getSpecificAccount(int id) {
		return accountInfoDao.oneType(id);
	}

	public List<AccountInfo> accsForUser(String uid) {
		List<AccountInfo> accs = accountInfoDao.findForUser(uid);
		return accs;
	}

	public String accTypeInfo(int x) {
		return accountInfoDao.getAccType(x);
	}
	
	public AccountInfo update(AccountInfo ai) {
		return accountInfoDao.specificBal(ai.getOwner(), ai.getUserId());
	}

	public AccountInfo saveChange(AccountInfo x) {
		AccountInfo ai = null;
		if (accountInfoDao.specificBal(ai.getOwner(), ai.getUserId()) == null) {
			ai = accountInfoDao.save(x);
		}
		return ai;
	}

	public int balance(AccountInfo ai) {
		return accountInfoDao.specificBal(ai.getOwner(), ai.getUserId()).getBalance();
	}

	public void withdraw(int w, AccountInfo ai) {
		int b = ai.getBalance();
		if (w <= b && w >= 0) {
			List<AccountInfo> accs = getSpecificAccount(ai.getTypeId());
			int y = w;
			for (int i = 0; i < accs.size(); i++) {
				int numOut = y;
				if (numOut <= 0) {
					break;
				} else if (numOut > accs.get(i).getBalance()) {
					numOut = accs.get(i).getBalance();
				}
				accountInfoDao.withdraw(numOut, accs.get(i).getAccountId());
				y -= numOut;
			}
		} else {
			System.out.println("Please do not do that...");
		}
	}

	public int deposit(int d, AccountInfo ai) {
		int x = 0;
		if (d > 0) {
			accountInfoDao.deposit(d, ai.getAccountId());
		} else {
			System.out.println("Please do not do that");
		}
		return x;
	}
	
	public boolean delete (AccountInfo ai) {
		return accountInfoDao.delete(ai.getAccountId());
	}
	
}
