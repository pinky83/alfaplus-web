package org.pinky83.alfaplus.service;

import org.junit.Test;
import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;

import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;
import static org.pinky83.alfaplus.AuthorizedUser.DOCTOR_ID;
import static org.pinky83.alfaplus.AuthorizedUser.GUEST_ID;
import static org.pinky83.alfaplus.ImageTestData.IMAGES;
import static org.pinky83.alfaplus.PatientTestData.*;
import static org.pinky83.alfaplus.PatientTestData.MATCHER;
import static org.pinky83.alfaplus.ImageTestData.IMAGE_MODEL_MATCHER;

/**
 * Created by Дмитрий on 23.12.2016.
 *
 */
public class PatientServiceTest extends AbstractServiceTest {

    @Autowired
    private PatientService service;
    @Autowired
    private ImageService imageService;

    @Test
    public void testGet() throws Exception {
        Patient actual = service.getById(PATIENT7.getId(), GUEST_ID);
        MATCHER.assertEquals(PATIENT7, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getById(LAST_PATIENT_ID + 1, DOCTOR_ID);
    }

    @Test
    public void testGetWithImages() throws Exception {
        Patient patient = service.getByIdWithImages(PATIENT8.getId(), DOCTOR_ID);
        IMAGE_MODEL_MATCHER.assertCollectionEquals(IMAGES, patient.getImages());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(PATIENT6.getId(), ADMIN_ID);
        thrown.expect(NotFoundException.class);
        service.getById(PATIENT6.getId(), ADMIN_ID);
    }

    @Test
    public void testCascadeDelete() throws Exception {
        service.delete(PATIENT8.getId(), ADMIN_ID);
        thrown.expect(NotFoundException.class);
        IMAGES.forEach(image -> imageService.getById(image.getId(), ADMIN_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(LAST_PATIENT_ID + 10, ADMIN_ID);
    }

    @Test
    public void testSave() throws Exception {
        Patient created = service.create(getCreated(), ADMIN_ID);
        Patient actual = service.getById(created.getId(), ADMIN_ID);
        MATCHER.assertEquals(created, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        Patient updated = getUpdated();
        service.update(getUpdated(), ADMIN_ID);
        MATCHER.assertEquals(updated, service.getById(FIRST_PATIENT_ID+1, ADMIN_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + (FIRST_PATIENT_ID-2));
        Patient expected = service.getById(FIRST_PATIENT_ID-2, DOCTOR_ID);
        service.update(expected, DOCTOR_ID);
    }

    @Test
    public void testGetBetween() throws Exception {
        Collection<Patient> tested =  service.getFilteredAll(LocalDate.of(1916, Month.JANUARY, 1), LocalDate.of(1919, Month.JANUARY, 1), DOCTOR_ID);
        Collection<Patient> dest = service.getAllWithImages(tested, DOCTOR_ID);
        dest.stream().forEach(patient -> {
            System.out.println(patient.toString());
            for (Image image: patient.getImages()
                 ) {
                System.out.println("    " + image.toString());
            }
        });
        System.out.println("Всего - " + tested.size());
    }

    @Test
    public void testGetByName() throws Exception {
        Collection<Patient> tested = service.getAllByName("Яковенко Дмитро", DOCTOR_ID);
        Collection<Patient> dest = service.getAllWithImages(tested, DOCTOR_ID);
        dest.stream().forEach(patient -> {
            System.out.println(patient.toString());
            for (Image image: patient.getImages()
                    ) {
                System.out.println("    " + image.toString());
            }
        });
        System.out.println("Всего - " + tested.size());
    }
}
