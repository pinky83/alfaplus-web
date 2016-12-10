package org.pinky83.alfaplus.repository;

import org.pinky83.alfaplus.model.User;

import java.util.List;

/**
 * Created by Дмитрий on 10.12.2016.//
 */
public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
