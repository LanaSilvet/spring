package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);

    List<User> getAllUsers();

    void deleteUser(int userId);

    User updateUser(int id, User user);

    User getUser(int id);
}
