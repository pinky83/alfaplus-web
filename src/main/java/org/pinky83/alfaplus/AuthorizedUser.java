package org.pinky83.alfaplus;

/**
 * Created by Дмитрий on 15.12.2016./
 */
public class AuthorizedUser {
    public static final int ADMIN_ID = 1;
    public static final int DOCTOR_ID = 2;
    public static final int GUEST_ID = 3;

    public static int id() {
        return ADMIN_ID;
    }
}
