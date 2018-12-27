package com.p1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.p1.pojos.Reimbursement;
import com.p1.util.ConnectionFactory;

public class ReimbursementDao implements Dao<Reimbursement, Integer> {

	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> Reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from reimbursement order by submitted";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setId(rs.getInt(1));
				temp.setAmount(rs.getDouble(2));
				temp.setSubmitted(rs.getTimestamp(3));
				temp.setResolved(rs.getTimestamp(4));
				temp.setDescription(rs.getString(5));
				temp.setAuthor(rs.getInt(6));
				temp.setResolver(rs.getInt(7));
				temp.setStatus_id(rs.getInt(8));
				temp.setType_id(rs.getInt(9));
				Reimbursements.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Reimbursements;
	}

	public List<Reimbursement> findByAuthor(int author) {
		List<Reimbursement> Reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from reimbursement where author = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, author);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reimbursement g = new Reimbursement();
				g.setId(rs.getInt(1));
				g.setAmount(rs.getDouble(2));
				g.setSubmitted(rs.getTimestamp(3));
				g.setResolved(rs.getTimestamp(4));
				g.setDescription(rs.getString(5));
				g.setAuthor(rs.getInt(6));
				g.setResolver(rs.getInt(7));
				g.setStatus_id(rs.getInt(8));
				g.setType_id(rs.getInt(9));
				Reimbursements.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Reimbursements;
	}

	@Override
	public Reimbursement save(Reimbursement obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO Reimbursement (NAME) VALUES(?)";
			String[] keyNames = { "id" };
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			int numRows = ps.executeUpdate();
			if (numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while (pk.next()) {
					obj.setId(pk.getInt(1));
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Reimbursement update(Reimbursement obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "update reimbursement set status_id = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj.getStatus_id());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Reimbursement obj) {
	}

	@Override
	public Reimbursement create(Reimbursement obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "INSERT INTO reimbursement (amount,submitted,resolved,description,author,resolver,status_id,type_id) VALUES(?,?,?,?,?,?,?,?)";
			String[] keyNames = { "id" };
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setDouble(1, obj.getAmount());
			ps.setTimestamp(2, obj.getSubmitted());
			ps.setTimestamp(3, obj.getResolved());
			ps.setString(4, obj.getDescription());
			ps.setInt(5, obj.getAuthor());
			ps.setInt(6, obj.getResolver());
			ps.setInt(7, obj.getStatus_id());
			ps.setInt(8, obj.getType_id());
			int numRows = ps.executeUpdate();
			if (numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while (pk.next()) {
					obj.setId(pk.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public List<Reimbursement> findById2(int id) {
		List<Reimbursement> r = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from reimbursement where id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reimbursement g = new Reimbursement();
				g.setId(rs.getInt(1));
				g.setAmount(rs.getDouble(2));
				g.setSubmitted(rs.getTimestamp(3));
				g.setResolved(rs.getTimestamp(4));
				g.setDescription(rs.getString(5));
				g.setAuthor(rs.getInt(6));
				g.setResolver(rs.getInt(7));
				g.setStatus_id(rs.getInt(8));
				g.setType_id(rs.getInt(9));
				r.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Reimbursement findById(Integer id) {
		Reimbursement r = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from reimbursement where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = new Reimbursement();
				r.setId(rs.getInt(1));
				r.setAmount(rs.getDouble(2));
				r.setSubmitted(rs.getTimestamp(3));
				r.setResolved(rs.getTimestamp(4));
				r.setDescription(rs.getString(5));
				r.setAuthor(rs.getInt(6));
				r.setResolver(rs.getInt(7));
				r.setStatus_id(rs.getInt(8));
				r.setType_id(rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}