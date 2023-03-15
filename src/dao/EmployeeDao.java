package dao;

import java.util.List;

import entity.Staff;
import entity.StaffDto;

public interface EmployeeDao {
	 List<StaffDto> findALL(int currPage) throws Exception;
	 void insertEmp(Staff e) throws Exception;
	 void deleteEmpById(int id) throws Exception;
	 void updateEmp(Staff e) throws Exception;
	 StaffDto findEmpById(int id) throws Exception;

	int findCount() throws Exception;

	StaffDto findEmpByUId(int uid);

	StaffDto findEmpByName(String salesmenName);

    String selectStaffNameByCarId(Integer getcId);
}
