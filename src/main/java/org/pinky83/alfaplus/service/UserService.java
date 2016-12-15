package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.User;

import java.util.List;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface UserService {

    List<User> getAll();

    User get(int id);

    User getByEmail(String email);

    void delete(int id);

    User create(User user);

    void update(User newT);
}
