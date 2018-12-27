package com.p1.test;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.p1.dao.ReimbursementDao;
import com.p1.dao.UsersDao;
import com.p1.pojos.Reimbursement;
import com.p1.pojos.Users;

public class p1Test {
	
	public static ReimbursementDao rDao = new ReimbursementDao();
	public static UsersDao uDao = new UsersDao();
	
	@Test
	public void findAllRTest() {
		List<Reimbursement>r =  rDao.findAll(); 
		assertNotNull(r);	
	}
	
	@Test
	public void findRByIdTest() {
		List<Reimbursement> r =  rDao.findById2(1); 
		assertNotNull(r);	
	}

	@Test
	public void findAllUTest() {
		List<Users> u =  uDao.findAll(); 
		assertNotNull(u);	
	}
	
	@Test
	public void findUByIdTest() {
		Users u =  uDao.findById(1); 
		assertNotNull(u);	
	}
}
