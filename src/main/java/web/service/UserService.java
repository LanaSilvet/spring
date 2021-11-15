package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    void deleteUser(int userId);

    User updateUser(int id, User user);

    User getUser(int id);
}
