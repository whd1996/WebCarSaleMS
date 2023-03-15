package dao.impl;
import dao.OrderDao;
import entity.Order;
import entity.OrderDto;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void addOrder(Order order) {
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into `order` (c_id, s_id, sale_date)\n" +
                    "values (?,?,?);");
            ps.setInt(1,order.getcId());
            ps.setInt(2,order.getsId());
            ps.setString(3,order.getSaleDate());
            ps.executeUpdate();
            JDBCUtils.close(ps, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<OrderDto> selectCustomerAllOrders(Integer sid) {
        ArrayList<OrderDto> list = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(" SELECT o.id,s.`name` customerName, \n" +
                    " c.car_name,c.id cid,c.price,c.status,o.sale_date from `order` o,car c,staff s where s.id=? and s.id=o.s_id and o.c_id=c.id");
            ps.setInt(1,sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OrderDto orderDto = new OrderDto();
                orderDto.setId(rs.getInt("id"));
                orderDto.setcId(rs.getInt("cid"));
                orderDto.setStatus(rs.getString("status"));
                orderDto.setCustomerName(rs.getString("customerName"));
                orderDto.setCarName(rs.getString("car_name"));
                orderDto.setCost(rs.getDouble("price"));
                orderDto.setSaleDate(rs.getString("sale_date"));
                list.add(orderDto);
            }
            JDBCUtils.close(ps, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Order findOrderById(int id) {
        Order order=null;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("select c_id,s_id,sale_date from `order` " +
                    "where id=?;");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                 order = new Order();
                order.setId(id);
                order.setcId(rs.getInt("c_id"));
                order.setsId(rs.getInt("s_id"));
                order.setSaleDate(rs.getString("sale_date"));
            }
            JDBCUtils.close(ps, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public void DeleteOrderById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("delete from `order` where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps, conn);
        }
    }

    @Override
    public ArrayList<OrderDto> selectAllOrders() {

        ArrayList<OrderDto> list = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(" SELECT o.id,s.`name` customerName, \n" +
                    "c.car_name,c.id cid,c.price,c.status,o.sale_date from `order` o,car c,staff s where s.id=o.s_id and o.c_id=c.id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OrderDto orderDto = new OrderDto();
                orderDto.setId(rs.getInt("id"));
                orderDto.setcId(rs.getInt("cid"));
                orderDto.setCustomerName(rs.getString("customerName"));
                orderDto.setCarName(rs.getString("car_name"));
                orderDto.setCost(rs.getDouble("price"));
                orderDto.setStatus(rs.getString("status"));
                orderDto.setSaleDate(rs.getString("sale_date"));
                list.add(orderDto);
            }
            JDBCUtils.close(ps, conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
