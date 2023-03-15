package dao;

import entity.Order;
import entity.OrderDto;

import java.util.ArrayList;

public interface OrderDao {

    void addOrder(Order order);

    ArrayList<OrderDto> selectCustomerAllOrders(Integer sid);

    Order findOrderById(int id);

    void DeleteOrderById(int id);

    ArrayList<OrderDto> selectAllOrders();

}