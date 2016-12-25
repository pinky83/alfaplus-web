package org.pinky83.alfaplus.service;

import org.junit.Test;
import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

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
}
