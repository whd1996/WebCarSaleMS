package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.UserDao;
import util.JDBCUtils;
import entity.User;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("insert into user(username,password,role) values(?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getRole());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(ps, conn);
		}
	}

	public void deleteUserByID(int ID) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("delete from user where id=?");
			ps.setInt(1, ID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(ps, conn);
		}
	}
	public void updateUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("update user set username=?,password=?,role=? where id=?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getRole());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(ps, conn);
		}
	}
	
	public ArrayList<User> findAllUsers() {
		ArrayList<User> userlist = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select * from user");
			rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(); //一定要加,否则会发生空指针异常
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
				userlist.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps, conn);
		}
		return userlist;
	}

	public User findUserByUsername(String username) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select * from user where username=?");
			ps.setString(1, username);
			//此时的rs处于第一行记录的上面,指向null
			rs = ps.executeQuery();
			//rs.next()后,rs指向第一行记录
			while(rs.next()) {
				//此处必须new一个user,否则报null异常
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRole(rs.getInt("role"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps, conn);
		}
		return user;
	}

	@Override
	public User findUserByUserId(String Id) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select * from user where id=?");
			ps.setString(1, Id);
			//此时的rs处于第一行记录的上面,指向null
			rs = ps.executeQuery();
			//rs.next()后,rs指向第一行记录
			while(rs.next()) {
				//此处必须new一个user,否则报null异常
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps, conn);
		}
		return user;
	}

	public User login(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from user where username=? and password=?";
			//PreparedStatement编译并发送SQL语句,如用Statement会被SQL注入式攻击
			ps = conn.prepareStatement(sql);
			//?赋值
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user=new User();
				user.setUsername(rs.getString("username"));
				user.setRole(rs.getInt("role"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, ps, conn);
		}
		return user;
	}
}
