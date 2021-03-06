package org.pinky83.alfaplus;

import org.pinky83.alfaplus.matcher.ModelMatcher;
import org.pinky83.alfaplus.model.Role;
import org.pinky83.alfaplus.model.User;

import java.util.Objects;

import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;
import static org.pinky83.alfaplus.AuthorizedUser.DOCTOR_ID;

/**
 * Created by Дмитрий on 23.12.2016.
 *
 */
public class UserTestData {
    //public static final int ADMIN_ID = AuthorizedUser.id();
    //public static final int DOCTOR_ID = ADMIN_ID;

    public static final User USER = new User(DOCTOR_ID, "User", "user@yandex.ru", "password", Role.ROLE_DOCTOR);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(User.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
//                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );
}
