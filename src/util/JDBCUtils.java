package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
	private static String url;
	private static String user;
	private static String password;
	private static Properties props = new Properties();

	static {
		ClassLoader classLoader = JDBCUtils.class.getClassLoader();
		InputStream in = classLoader.getResourceAsStream("dao.properties");
		try {
			props.load(in);
			user = props.getProperty("user");
			url =  props.getProperty("url");
			Class.forName(props.getProperty("driver"));
			password=props.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static String getObject(String key) {
		return props.getProperty(key);
	}
	
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
			try {
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(PreparedStatement ps, Connection conn) {
			try {
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
