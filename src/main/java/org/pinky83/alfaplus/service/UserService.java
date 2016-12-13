package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.User;

import java.util.List;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface UserService {

    List<User> getAll();

    User getById(int id);

    void delete(User user);

    void create(User user);

    void update(Integer id, User newT);
}
