package org.pinky83.alfaplus;

import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

/**
 * Created by Дмитрий on 20.01.2017.
 *
 */
public class TestUtil {
    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }
}
