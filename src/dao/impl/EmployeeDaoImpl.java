package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Staff;
import entity.StaffDto;
import util.JDBCUtils;
import dao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {

	public List<StaffDto> findALL(int page) throws Exception {
		ArrayList<StaffDto> list = new ArrayList<StaffDto>();
		Connection conn = JDBCUtils.getConnection();

		PreparedStatement ps = conn.prepareStatement("select staff.id,`name`,gender,age,contact_number,address,email,IC_Number,`role` " +
				"from staff left join user u on u.id = staff.uid order by id limit ?,?");
		ps.setInt(1, (page - 1) * 5);
		ps.setInt(2, 5);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			StaffDto e = new StaffDto();
			setStaff(e, rs);
			switch (rs.getInt("role")){
				case 2 :  e.setUserRole("ManagingStaff"); break;
				case 1 :  e.setUserRole("SalesMen"); break;
				case 0 :  e.setUserRole("Customers"); break;
			}
			list.add(e);
		}
		JDBCUtils.close(rs, ps, conn);
		return list;
	}
	
	public void insertEmp(Staff e) throws Exception {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into staff (name, gender, age, contact_number, address, email, IC_Number, uid)" +
				"values (?,?,?,?,?,?,?,?);");
		ps.setString(1, e.getName());
		ps.setString(2, e.getGender());
		ps.setInt(3,e.getAge());
		ps.setString(4, e.getContactNumber());
		ps.setString(5, e.getAddress());
		ps.setString(6, e.getEmail());
		ps.setString(7, e.getIcNumber());
		ps.setInt(8,e.getUid());
		ps.executeUpdate();
		JDBCUtils.close(ps, conn);
		
	}
	
	public void deleteEmpById(int id) throws Exception {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from staff where id=?");
		ps.setInt(1,id);
		ps.executeUpdate();
		JDBCUtils.close(ps, conn);
	}
	
	public void updateEmp(Staff e) throws Exception {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement("update staff set name=?,age=?,gender=?,contact_number=?,address=?,email=?,IC_Number=?" +
				"where id=?");
		ps.setString(1, e.getName());
		ps.setInt(2,e.getAge());
		ps.setString(3,e.getGender());
		ps.setString(4,e.getContactNumber());
		ps.setString(5,e.getAddress());
		ps.setString(6,e.getEmail());
		ps.setString(7,e.getIcNumber());
		ps.setInt(8,e.getId());
		ps.executeUpdate();
		
		JDBCUtils.close(ps, conn);
	}
	
	public StaffDto findEmpById(int id) throws Exception {
		StaffDto e = null;
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement("select staff.id,uid,`name`,gender,age,contact_number,address,email,IC_Number,`role`" +
				"from staff left join user u on u.id = staff.uid where staff.id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			e = new StaffDto();
			setStaff(e, rs);
			e.setUid(rs.getInt("uid"));
		}
		JDBCUtils.close(rs, ps, conn);
		return e;
		
	}

	private void setStaff(StaffDto e, ResultSet rs) throws SQLException {
		e.setId(rs.getInt("id"));
		e.setName(rs.getString("name"));
		e.setGender(rs.getString("gender"));
		e.setAddress(rs.getString("address"));
		e.setAge(rs.getInt("age"));
		e.setContactNumber(rs.getString("contact_number"));
		e.setAddress(rs.getString("address"));
		e.setEmail(rs.getString("email"));
		e.setIcNumber(rs.getString("IC_Number"));
	}

	public int findCount() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement("select count(*) from staff");
		ResultSet rs = ps.executeQuery();
		int count =0;
		rs.next();
		count=rs.getInt(1);
		//System.out.println(count);
		return count;
	}

	@Override
	public StaffDto findEmpByUId(int uid) {
		StaffDto e = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select staff.id,uid,`name`,gender,age,contact_number,address,email,IC_Number,`role`" +
					"from staff left join user u on u.id = staff.uid where staff.uid=?");
			ps.setInt(1, uid);
			rs = ps.executeQuery();

			while(rs.next()) {
				e = new StaffDto();
				setStaff(e, rs);
				e.setUid(rs.getInt("uid"));
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		JDBCUtils.close(rs, ps, conn);
		return e;
	}

	@Override
	public StaffDto findEmpByName(String salesmenName) {
		StaffDto e = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select staff.id,uid,`name`,gender,age,contact_number,address,email,IC_Number,`role`" +
					"from staff left join user u on u.id = staff.uid where staff.name=?");
			ps.setString(1, salesmenName);
			rs = ps.executeQuery();
			if(rs.next()) {
				e = new StaffDto();
				setStaff(e, rs);
				e.setUid(rs.getInt("uid"));
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			JDBCUtils.close(rs, ps, conn);
		}
		return e;
	}

	@Override
	public String selectStaffNameByCarId(Integer cId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name=null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select distinct name from car left join staff s on car.s_id = s.id where car.id=?");
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			if(rs.next()) {
				name=rs.getString("name");
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		JDBCUtils.close(rs, ps, conn);
		return name;
	}

}
