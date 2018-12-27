package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojos.AccountInfo;
import pojos.User;
import util.ConnectionFactory;

public class AccountInfoDao implements Dao<AccountInfo, Integer> {

	@Override
	public List<AccountInfo> findAll() {
		List<AccountInfo> accs = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from accountinfo";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				AccountInfo ai = new AccountInfo();
				ai.setAccountId(rs.getInt(1));
				ai.setTypeId(rs.getInt(2));
				ai.setOwner(rs.getString(3));
				ai.setBalance(rs.getInt(4));
				ai.setUserId(rs.getInt(5));
				accs.add(ai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accs;
	}

	@Override
	public AccountInfo save(AccountInfo obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "insert into accountinfo (typeid, owner, balance, userid) values (?, ?, ?, ?)";
			String[] keys = { "accountid" };
			PreparedStatement ps = conn.prepareStatement(sql, keys);
			ps.setInt(1, obj.getTypeId());
			ps.setString(2, obj.getOwner());
			ps.setInt(3, obj.getBalance());
			ps.setInt(4, obj.getUserId());
			int x = ps.executeUpdate();
			if (x > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					obj.setAccountId(rs.getInt(1));
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountInfo update(AccountInfo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AccountInfo> findForUser(String id) {
		List<AccountInfo> accs = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from accountinfo where owner = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AccountInfo ai = new AccountInfo();
				ai.setAccountId(rs.getInt(1));
				ai.setTypeId(rs.getInt(2));
				ai.setOwner(rs.getString(3));
				ai.setBalance(rs.getInt(4));
				ai.setUserId(rs.getInt(5));
				accs.add(ai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accs;
	}

	public List<AccountInfo> oneType(int id) {
		List<AccountInfo> accs = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from accountinfo where typeid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AccountInfo ai = new AccountInfo();
				ai.setAccountId(rs.getInt(1));
				ai.setTypeId(rs.getInt(2));
				ai.setOwner(rs.getString(3));
				ai.setBalance(rs.getInt(4));
				ai.setUserId(rs.getInt(5));
				accs.add(ai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accs;
	}

	public AccountInfo specificBal(String usr, int uid) {
		AccountInfo ai = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from accountinfo where owner = ? and userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usr);
			ps.setInt(2, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ai = new AccountInfo();
				ai.setAccountId(rs.getInt(1));
				ai.setTypeId(rs.getInt(2));
				ai.setOwner(rs.getString(3));
				ai.setBalance(rs.getInt(4));
				ai.setUserId(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ai;
	}

	public String getAccType(int id) {
		String x = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select type from accounttype where typeid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				x = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}

	public void deposit(int num, int id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "{call deposit_procedure(?,?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, num);
			cs.setInt(2, id);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void withdraw(int num, int id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "{call withdraw_procedure(?,?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, num);
			cs.setInt(2, id);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean delete(int id) {
		boolean x = false;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from accountinfo where accountid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			x = true;
		} catch (Exception e) {

		}
		return x;
	}

}
