package org.pinky83.alfaplus.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Дмитрий on 29.11.2016./
 */
public class User extends NamedEntity{
    protected String password;
    protected String email;
    protected boolean enabled = true;
    protected Date registered = new Date();
    protected Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String password, String email, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password,  true, EnumSet.of(role, roles));
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                '}';
    }
}
