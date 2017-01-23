package org.pinky83.alfaplus.web.patient;

import org.junit.Test;
import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.web.AbstractControllerTest;
import org.pinky83.alfaplus.web.json.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;
import static org.pinky83.alfaplus.ImageTestData.IMAGES;
import static org.pinky83.alfaplus.ImageTestData.IMAGE_MODEL_MATCHER;
import static org.pinky83.alfaplus.PatientTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Дмитрий on 18.01.2017.
 *
 */
public class PatientRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = PatientRestController.REST_URL + "/";

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL+ PATIENT1.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(PATIENT1));

        mockMvc.perform(get(REST_URL+ "2"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetWithImages() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL  + "full/" + PATIENT8.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Patient actual = MATCHER.fromJsonAction(action);
        MATCHER.assertEquals(actual, PATIENT8);
        IMAGE_MODEL_MATCHER.assertCollectionEquals(actual.getImages(), IMAGES);
    }

    @Test
    public void testGetAllByName() throws Exception {
        List<Patient> expected = new ArrayList<>();
        expected.add(PATIENT8);
        mockMvc.perform(get(REST_URL  + "byName/" + PATIENT8.getName()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(expected));
    }

    @Test
    public void testGetFilteredAll() throws Exception {
        List<Patient> expected = new ArrayList<>();
        expected.add(PATIENT9);
        mockMvc.perform(get(REST_URL  + "getBetween/")
                    .param("date1", PATIENT9.getBirthDate().toString())
                    .param("date2", PATIENT9.getBirthDate().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(expected));
    }

    @Test
    public void testCreate() throws Exception {
        Patient created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated())
                .andDo(print());

        Patient returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        Patient fromBase = patientService.getById(returned.getId(), ADMIN_ID);
        MATCHER.assertEquals(returned, fromBase);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + PATIENT8.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL+ PATIENT8.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdate() throws Exception {
        Patient updated = getUpdated();
        updated.setThisId(PATIENT8.getId());

        mockMvc.perform(put(REST_URL + PATIENT8.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk())
                .andDo(print());

        MATCHER.assertEquals(updated, patientService.getById(PATIENT8.getId(), ADMIN_ID));
    }

}