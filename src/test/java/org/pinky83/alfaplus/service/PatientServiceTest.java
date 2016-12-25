package org.pinky83.alfaplus.service;

import org.junit.Assert;
import org.junit.Test;
import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.pinky83.alfaplus.PatientTestData.*;
import static org.pinky83.alfaplus.UserTestData.DOCTOR_ID;
import static org.pinky83.alfaplus.PatientTestData.MATCHER;

/**
 * Created by Дмитрий on 23.12.2016.
 *
 */
public class PatientServiceTest extends AbstractServiceTest{

    @Autowired
    protected PatientService service;

    @Test
    public void testGet() throws Exception {
        Patient actual = service.getById(PATIENT7.getId(), DOCTOR_ID);
        MATCHER.assertEquals(PATIENT7, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getById(LAST_PATIENT_ID+1, DOCTOR_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(PATIENT3.getId(), DOCTOR_ID);
        thrown.expect(NotFoundException.class);
        service.getById(PATIENT3.getId(), DOCTOR_ID);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(LAST_PATIENT_ID+10, DOCTOR_ID);
    }

    @Test
    public void testSave() throws Exception {
        Patient created = getCreated();
        service.create(created, DOCTOR_ID);
        List<Patient> full_pack = service.getAll(DOCTOR_ID).stream().collect(Collectors.toList());
        Collections.reverse(full_pack);
        Patient actual = full_pack.stream().findFirst().get();
        Assert.assertEquals(created.getName(), actual.getName());
    }
}
