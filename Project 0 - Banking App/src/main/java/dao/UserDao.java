package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.internal.OracleTypes;
import pojos.User;
import util.ConnectionFactory;

public class UserDao implements Dao<User, Integer> {

	@Override
	public User save(User obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into userinfo (firstname, lastname, username, password) values (?, ?, ?, ?)";
			String[] keys = { "UserId" };
			PreparedStatement ps = conn.prepareStatement(sql, keys);
			ps.setString(1, obj.getFirstName());
			ps.setString(2, obj.getLastName());
			ps.setString(3, obj.getUserName());
			ps.setString(4, obj.getUserName());
			int numRows = ps.executeUpdate();
			if (numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while (pk.next()) {
					obj.setId(pk.getInt(1));
				}
			}
			conn.commit();
		} catch (SQLException e) {
		}
		return obj;
	}

	@Override
	public User findByUserName(String userName) {
		User u = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from userInfo where userName = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setUserName(rs.getString(4));
				u.setPassword(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{ call get_all_users(?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);
			while(rs.next()) {
				User temp = new User();
				temp.setId(rs.getInt("userId"));
				temp.setFirstName(rs.getString(2));
				temp.setLastName(rs.getString(3));
				temp.setUserName(rs.getString(4));
				temp.setPassword(rs.getString(5));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
