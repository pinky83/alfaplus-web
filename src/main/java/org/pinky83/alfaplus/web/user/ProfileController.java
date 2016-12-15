package org.pinky83.alfaplus.web.user;

import org.pinky83.alfaplus.AuthorizedUser;
import org.pinky83.alfaplus.model.User;
import org.springframework.stereotype.Controller;

/**
 * Created by Дмитрий on 15.12.2016./
 */
@Controller
public class ProfileController extends AbstractUserController{

    public User get() {
        return super.get(AuthorizedUser.id());
    }


    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @Override
    public void update(User user, int id) {
        super.update(user, AuthorizedUser.id());
    }
}
