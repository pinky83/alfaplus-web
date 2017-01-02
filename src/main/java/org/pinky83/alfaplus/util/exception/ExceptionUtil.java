package org.pinky83.alfaplus.util.exception;


import java.util.Arrays;
import java.util.List;

/**
 * User: gkislin
 * Date: 14.05.2014
 */
public class ExceptionUtil {
    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) throw new NotFoundException("Not found entity with " + msg);
    }

    public static void checkUserId(int checkedId, Integer... expectedId) {
        List<Integer> test = Arrays.asList(expectedId);
        if (!(test.contains(checkedId))) throw new AccessViolationException("User with id=" + checkedId
                + " does not have permission to perform this operation");
    }
}
