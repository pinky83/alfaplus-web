package org.pinky83.alfaplus.web.user;

import org.pinky83.alfaplus.model.User;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Дмитрий on 15.12.2016./
 */
@Controller
public class AdminController extends AbstractUserController{
    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }
}
