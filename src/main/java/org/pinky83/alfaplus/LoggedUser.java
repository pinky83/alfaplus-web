package org.pinky83.alfaplus;

import org.pinky83.alfaplus.model.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Дмитрий on 13.12.2016./
 */
public class LoggedUser {
    private int id;
    private Set<Role> Roles = new HashSet<>();

    public LoggedUser() {
        this.id = 1;
        Roles.add(Role.ROLE_ADMIN);
        Roles.add(Role.ROLE_DOCTOR);
    }

    public int getId() {
        return id;
    }

    public Set<Role> getRoles() {
        return Roles;
    }
}
