package com.tushar.User.Service.Service;

import com.tushar.User.Service.Model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    String deleteUser(Long id);
    User updateUser(User user, Long id);

}
