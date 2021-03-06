/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connect.DBConnect;
import model.User;

/**
 *
 * @author NVB
 */
public class UserDAO {

	// check email exists.
	public boolean checkEmail(String email) {
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM users WHERE email = '?'";
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				connection.close();
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	// add User.
	public boolean insertUser(User u) {
		Connection connection = DBConnect.getConnection();
		String sql = "INSERT INTO users(name, email, password, address, phone, role) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getPhone());
			ps.setLong(6, u.getRole());
			ps.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	// edit User by id.
	public boolean editUser(User u) {
		Connection connection = DBConnect.getConnection();
		String sql = "UPDATE users set name = ?, email = ?, password = ?, address= ? , phone= ? , role= ? WHERE id = ?";
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getPhone());
			ps.setLong(6, u.getRole());
			ps.setLong(7, u.getId());
			ps.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	// check login user.
	public User loginUser(String email, String password) {
		Connection con = DBConnect.getConnection();
		String sql = "select * from users where email=? and password=? and role= '0'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getLong("role"));
				
				con.close();
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// check login.
		public User loginAdmin(String email, String password) {
			Connection con = DBConnect.getConnection();
			String sql = "select * from users where email=? and password=? and role!= '0'";
			PreparedStatement ps;
			try {
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, email);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getLong("id"));
					u.setName(rs.getString("name"));
					u.setPassword(rs.getString("password"));
					u.setAddress(rs.getString("address"));
					u.setPhone(rs.getString("phone"));
					u.setEmail(rs.getString("email"));
					u.setRole(rs.getLong("role"));
					
					con.close();
					return u;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	public User getUserById(long userID) {
		User u = new User();
		try {
			Connection connection = DBConnect.getConnection();
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setLong(1, userID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getLong("role"));
			}
			connection.close();
			return u;
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return u;
	}

	public ArrayList<User> getAllCustomer() {

		ArrayList<User> allUser = new ArrayList<>();

		try {
			Connection connection = DBConnect.getConnection();
			String sql = "SELECT * FROM users where role = 0";
			PreparedStatement ps = connection.prepareCall(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getLong("role"));
				allUser.add(u);
			}
			connection.close();
			return allUser;
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return allUser;
	}
	
	public ArrayList<User> getAllUser() {

		ArrayList<User> allUser = new ArrayList<>();

		try {
			Connection connection = DBConnect.getConnection();
			String sql = "SELECT * FROM users where role != 0";
			PreparedStatement ps = connection.prepareCall(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getLong("role"));
				allUser.add(u);
			}
			connection.close();
			return allUser;
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return allUser;
	}

	public void deleteById(Long id) {
		try {
			Connection connection = DBConnect.getConnection();

			String query = "delete from users where id = ?";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setLong(1, id);
			preparedStmt.execute();

			connection.close();

		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		User user = new UserDAO().loginUser("nguyenvanbay@gmail.com", "123");
		if(user != null) {
			System.out.println(user.getName());
			System.out.println(user.getEmail());
			System.err.println(user.getPassword());
		}
	}

	public ArrayList<User> getWhere(String name, String email, String phone, String role) {
		
		String sql = "SELECT * FROM users where 1 = 1 ";
		
		if(name != "") {
			sql += " AND name like '%" + name + "%'";
		}
		
		if(email != "") {
			sql += " AND email like '%" + email + "%'";
		}
		
		if(phone != "") {
			sql += " AND phone = " + phone;
		}
		
		if(!role.equals("0")) {
			
			sql += " AND role = " + role;
		}
		
		System.out.println(sql);
		
		ArrayList<User> allUser = new ArrayList<>();

		try {
			Connection connection = DBConnect.getConnection();
			
			PreparedStatement ps = connection.prepareCall(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getLong("role"));
				allUser.add(u);
			}
			connection.close();
			return allUser;
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return allUser;
	}
}
