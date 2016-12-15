package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.User;
import org.pinky83.alfaplus.repository.mock.MockUserRepository;
import org.pinky83.alfaplus.util.exception.ExceptionUtil;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Дмитрий on 30.11.2016./
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private MockUserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(userRepository.get(id), id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(userRepository.delete(id),id);
    }

    @Override
    public User create(User user) {
       return userRepository.save(user);
    }

    @Override
    public void update(User newT) {
        userRepository.save(newT);
    }
}
