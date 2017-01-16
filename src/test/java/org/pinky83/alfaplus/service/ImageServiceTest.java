package org.pinky83.alfaplus.service;

import org.junit.Ignore;
import org.junit.Test;
import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.util.CollectionSizeFetcher;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;
import static org.pinky83.alfaplus.AuthorizedUser.DOCTOR_ID;
import static org.pinky83.alfaplus.AuthorizedUser.GUEST_ID;
import static org.pinky83.alfaplus.ImageTestData.*;

/**
 * Created by Дмитрий on 07.01.2017.
 *
 */
public class ImageServiceTest extends AbstractServiceTest {
    @Autowired
    private ImageService service;

    @Test
    @Ignore//too slow
    public void getAll() throws Exception {
        List<Image> actual = service.getAll(ADMIN_ID);
        assertEquals(actual.hashCode(), IMAGES_COLLECTION_HASH);
        assertEquals(actual.size(), N_OF_ELEMENTS);
        assertEquals(CollectionSizeFetcher.getCollectionSize(actual), SIZE_WITHOUT_LAZY_FIELDS);
    }

    @Test
    @Ignore//too slow - near 13 minutes
    public void getAllWithLazyFields() throws Exception {
        List<Image> test = service.getAll(ADMIN_ID);
        List<Image> actual = service.getAllWithLazyFields(test, ADMIN_ID);
        System.out.printf("Collection size = %d bytes%n", CollectionSizeFetcher.getCollectionSize(actual));//22742530 bytes
        System.out.printf("Number of elements = %d%n", actual.size());//61660
        System.out.printf("Hash Code = %d%n", actual.hashCode());//1176784001
        System.out.println("*********************************************");
    }

    @Test
    public void getById() throws Exception {
        Image actual = service.getById(IMAGE1.getId(), GUEST_ID);
        System.out.println(actual);
        IMAGE_MODEL_MATCHER.assertEquals(IMAGE1, actual);
    }

    @Test
    public void getByIdWithLazyFields() throws Exception {
        Image actual = service.getByIdWithLazyFields(IMAGE7.getId(), DOCTOR_ID);
        SERIES_MODEL_MATCHER.assertEquals(IMAGE7_SERIES, actual.getSeries());
        STUDY_MODEL_MATCHER.assertEquals(IMAGE7_STUDY, actual.getStudy());
    }

    @Test
    public void delete() throws Exception {
        service.delete(IMAGE6.getId(), ADMIN_ID);
        thrown.expect(NotFoundException.class);
        service.getById(IMAGE6.getId(), ADMIN_ID);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(LAST_IMAGE_ID + 10, ADMIN_ID);
    }

    @Test
    public void create() throws Exception {
        Image created = service.create(getCreated(), ADMIN_ID);
        Image actual = service.getById(created.getId(), ADMIN_ID);
        IMAGE_MODEL_MATCHER.assertEquals(created, actual);
    }

    @Test
    public void update() throws Exception {
        Image updated = getUpdated();
        service.update(getUpdated(), ADMIN_ID);
        IMAGE_MODEL_MATCHER.assertEquals(updated, service.getById(FIRST_IMAGE_ID+1, ADMIN_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + (FIRST_IMAGE_ID-2));
        Image expected = service.getById(FIRST_IMAGE_ID-2, DOCTOR_ID);
        service.update(expected, DOCTOR_ID);
    }

    @Test
    public void testGetAllByDate() throws Exception {
        LocalDate date = LocalDate.of(2014, 7, 22);
        List<Image> actual = service.getAllByDate(date, DOCTOR_ID);
        actual.forEach(System.out::println);
    }

    @Test
    public void testGetLastDayImages() throws Exception {
        List<Image> actual = service.getLastDay(DOCTOR_ID);
        actual.forEach(System.out::println);
    }

    @Test
    public void testGetPreviousPage() throws Exception {
        Page<Image> previous = service.getPreviousPage(new PageRequest(0, 70), LAST_IMAGE_ID, DOCTOR_ID);
        previous.getContent().forEach(System.out::println);
    }

    @Test
    public void testGetNextPage() throws Exception {
        Page<Image> next = service.getNextPage(new PageRequest(0, 70), 34568, DOCTOR_ID);
        next.getContent().forEach(System.out::println);
    }
}