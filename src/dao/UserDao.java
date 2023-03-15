package dao;

import entity.User;

import java.util.ArrayList;

public interface UserDao {
    void addUser(User user);
    void deleteUserByID(int ID);
    void updateUser(User user);
    //	public User findUserByID(int ID);
    User findUserByUsername(String username);
    User findUserByUserId(String Id);
    ArrayList<User> findAllUsers();
    User login(String username, String password);
}