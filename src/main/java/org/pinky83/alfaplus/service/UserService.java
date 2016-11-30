package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.User;

import java.util.List;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface UserService extends GenericService<User>{
    @Override
    List<User> getAll();

    @Override
    User getById(Integer id);

    @Override
    void delete(User user);

    @Override
    void create(User user);

    @Override
    void update(Integer id, User newT);
}
