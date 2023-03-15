package dao.impl;

import dao.CarDao;
import entity.Car;
import entity.CarDto;
import util.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDaoImpl implements CarDao {
    @Override
    public void addCar(Car car) {
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into car (car_name, age_limit, type, quality, price, status, s_id)" +
                    "values (?,?,?,?,?,?,?);");
          ps.setString(1,car.getCarName());
          ps.setInt(2,car.getAgeLimit());
          ps.setString(3,car.getType());
          ps.setString(4,car.getQuality());
          ps.setDouble(5,car.getPrice());
          ps.setString(6,car.getStatus());
          ps.setInt(7,car.getsId());
            ps.executeUpdate();
            JDBCUtils.close(ps, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<CarDto> findAllCars(int page) {
        ArrayList<CarDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select car.id,s_id,car_name,age_limit,`type`,quality,price,`status`,`name` from car " +
                    "left join staff s on s.id = car.s_id order by price desc limit ?,?");
            ps.setInt(1, (page - 1) * 5);
            ps.setInt(2, 5);
            rs = ps.executeQuery();
            while(rs.next()) {
                CarDto dto = new CarDto();
                setCarInfo(dto, rs);
                list.add(dto);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(rs, ps, conn);
        }
        return list;
    }
    @Override
    public int findCount() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement("select count(*) from car");
        ResultSet rs = ps.executeQuery();
        int count =0;
        rs.next();
        count=rs.getInt(1);
        return count;
    }

    @Override
    public ArrayList<String> findAllCarsSalesmenName() {
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select `name` from staff left JOIN `user` on user.id=staff.uid where user.role=1");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString("name"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(ps, conn);
        }
        return list;
    }

    @Override
    public void DeleteCarById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("delete from car where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps, conn);
        }
    }

    @Override
    public CarDto findCarById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        CarDto dto=null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select car.id,s_id,car_name,age_limit,`type`,quality,price,`status`,`name` from car " +
                    "left join staff s on s.id = car.s_id where car.id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                dto = new CarDto();
                setCarInfo(dto, rs);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps, conn);
        }
        return dto;
    }

    @Override
    public void updateCar(Car car) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps=conn.prepareStatement("update car set car_name=?,age_limit=?,type=?,quality=?,price=?,`status`=?,s_id=? where id=?;");
            ps.setString(1,car.getCarName());
            ps.setInt(2,car.getAgeLimit());
            ps.setString(3,car.getType());
            ps.setString(4,car.getQuality());
            ps.setDouble(5,car.getPrice());
            ps.setString(6,car.getStatus());
            ps.setInt(7,car.getsId());
            ps.setInt(8,car.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(ps,conn);
        }
    }

    @Override
    public ArrayList<Car> findStaffAllCars(Integer sid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList<Car> list = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select id,car_name,age_limit,`type`,quality,price,`status`,s_id from car " +
                    "where s_id=?");
            ps.setInt(1,sid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setCarName(rs.getString("car_name"));
                car.setAgeLimit(rs.getInt("age_limit"));
                car.setType(rs.getString("type"));
                car.setQuality(rs.getString("quality"));
                car.setPrice(rs.getDouble("price"));
                car.setStatus(rs.getString("status"));
                car.setsId(rs.getInt("s_id"));
                list.add(car);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(ps, conn);
        }
        return list;
    }

    @Override
    public ArrayList<CarDto> findAllCarsCanBuy(int page) {

        ArrayList<CarDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select car.id,s_id,car_name,age_limit,`type`,quality,price,`status`,`name` from car " +
                    "left join staff s on s.id = car.s_id where status='available' order by price desc limit ?,?");
            ps.setInt(1, (page - 1) * 5);
            ps.setInt(2, 5);
            rs = ps.executeQuery();
            while(rs.next()) {
                CarDto dto = new CarDto();
                setCarInfo(dto, rs);
                list.add(dto);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(rs, ps, conn);
        }
        return list;
    }

    private void setCarInfo(CarDto dto, ResultSet rs) throws SQLException {
        dto.setId(rs.getInt("id"));
        dto.setsId(rs.getInt("s_id"));
        dto.setCarName(rs.getString("car_name"));
        dto.setAgeLimit(rs.getInt("age_limit"));
        dto.setType(rs.getString("type"));
        dto.setQuality(rs.getString("quality"));
        dto.setPrice(rs.getDouble("price"));
        dto.setStatus(rs.getString("status"));
        dto.setSalesmenName(rs.getString("name"));
    }
}
