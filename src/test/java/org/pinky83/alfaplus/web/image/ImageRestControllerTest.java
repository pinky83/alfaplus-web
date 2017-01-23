package org.pinky83.alfaplus.web.image;

import org.junit.Assert;
import org.junit.Test;
import org.pinky83.alfaplus.TestUtil;
import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.util.CollectionSizeFetcher;
import org.pinky83.alfaplus.web.AbstractControllerTest;
import org.pinky83.alfaplus.web.json.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;
import static org.pinky83.alfaplus.ImageTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Дмитрий on 18.01.2017.
 *
 */
public class ImageRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = ImageRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL+ IMAGE1.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(IMAGE_MODEL_MATCHER.contentMatcher(IMAGE1));

        mockMvc.perform(get(REST_URL+ "2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL+ IMAGE1.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(get(REST_URL+ IMAGE1.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdate() throws Exception {
        Image updated = getUpdated();
        updated.setThisId(IMAGE1.getId());

        mockMvc.perform(put(REST_URL+ IMAGE1.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk())
                .andDo(print());

        IMAGE_MODEL_MATCHER.assertEquals(updated, imageService.getById(IMAGE1.getId(), ADMIN_ID));
    }

    @Test
    public void testCreate() throws Exception {
        Image created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated())
                .andDo(print());

        Image returned = IMAGE_MODEL_MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        Image fromBase = imageService.getById(returned.getId(), ADMIN_ID);
        IMAGE_MODEL_MATCHER.assertEquals(returned, fromBase);
    }

    @Test
    public void testGetWithLazyFields() throws Exception {
        Image expected = IMAGE7;
        expected.setSeries(IMAGE7_SERIES);
        expected.setStudy(IMAGE7_STUDY);

        ResultActions action = mockMvc.perform(get(REST_URL  + "full/" + expected.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Image actual = IMAGE_MODEL_MATCHER.fromJsonAction(action);
        SERIES_MODEL_MATCHER.assertEquals(IMAGE7_SERIES, actual.getSeries());
        STUDY_MODEL_MATCHER.assertEquals(IMAGE7_STUDY, actual.getStudy());
    }

    @Test
    public void testGetLastDay() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "last")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Image> result = JsonUtil.readValues(TestUtil.getContent(action), Image.class);
        Assert.assertEquals(result.hashCode(), LAST_PAGE_IMAGES_COLLECTION_HASH);
        Assert.assertEquals(result.size(), LAST_PAGE_N_OF_ELEMENTS);
        Assert.assertEquals(CollectionSizeFetcher.getCollectionSize(result), LAST_PAGE_SIZE_WITHOUT_LAZY_FIELDS);
    }

    @Test
    public void testGetAllByDate() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "byDate/2014-05-07")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Image> result = JsonUtil.readValues(TestUtil.getContent(action), Image.class);
        Assert.assertEquals(result.hashCode(), TEST_PAGE1_IMAGES_COLLECTION_HASH);
        Assert.assertEquals(result.size(), TEST_PAGE1_N_OF_ELEMENTS);
        Assert.assertEquals(ofNullable(result.get(0).getId()), ofNullable(TEST_PAGE1_N_OF_FIRST_ELEMENT));
        Assert.assertEquals(ofNullable(result.get(16).getId()), ofNullable(TEST_PAGE1_N_OF_LAST_ELEMENT));
    }

    @Test
    public void testGetNextPage() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "next/46564")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Image> result = JsonUtil.readValues(TestUtil.getContent(action), Image.class).subList(0,17);
        Assert.assertEquals(result.hashCode(), TEST_PAGE1_IMAGES_COLLECTION_HASH);
        Assert.assertEquals(result.size(), TEST_PAGE1_N_OF_ELEMENTS);
        Assert.assertEquals(ofNullable(result.get(0).getId()), ofNullable(TEST_PAGE1_N_OF_FIRST_ELEMENT));
        Assert.assertEquals(ofNullable(result.get(16).getId()), ofNullable(TEST_PAGE1_N_OF_LAST_ELEMENT));
    }

    @Test
    public void testGetPreviousPage() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "previous/46582")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Image> source = JsonUtil.readValues(TestUtil.getContent(action), Image.class);
        final int last = source.size();
        final int first = last - 17;
        List<Image> result = source.subList(first, last);
        Assert.assertEquals(result.hashCode(), TEST_PAGE1_IMAGES_COLLECTION_HASH);
        Assert.assertEquals(result.size(), TEST_PAGE1_N_OF_ELEMENTS);
        Assert.assertEquals(ofNullable(result.get(0).getId()), ofNullable(TEST_PAGE1_N_OF_FIRST_ELEMENT));
        Assert.assertEquals(ofNullable(result.get(16).getId()), ofNullable(TEST_PAGE1_N_OF_LAST_ELEMENT));
    }

}