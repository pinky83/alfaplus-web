package org.pinky83.alfaplus.web.patient;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;

/**
 * Created by Дмитрий on 15.12.2016./
 */
@Controller
public abstract class AbstractPatientController {
    private final Logger LOG = LoggerFactory.getLogger(AbstractPatientController.class);

    @Autowired
    private PatientService service;

    public Patient create(Patient patient) {
        int userId = ADMIN_ID;
        LOG.info("create patient {} for {} ", patient.getName(), userId);
        return service.create(patient, userId);
    }

    public void delete(int id) {
        int userId = ADMIN_ID;
        LOG.info("delete patient {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public Patient get(int id) {
        int userId = ADMIN_ID;
        LOG.info("get patient {} for User {}", id, userId);
        return service.getById(id, userId);
    }

    public Collection<Patient> getWithImages(int id) {
        int userId = ADMIN_ID;
        LOG.info("get patient {} with images for User {}", id, userId);
        Collection<Patient> result = new ArrayList<>();
        result.add(service.getByIdWithImages(id, userId));
        return result;
    }

    public Collection<Patient> getAll() {
        int userId = ADMIN_ID;
        LOG.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Collection<Patient> getAllWithImages(Collection<Patient> source) {
        int userId = ADMIN_ID;
        LOG.info("getAll with images for user {}", userId);
        return service.getAllWithImages(source, userId);
    }

    public Collection<Patient> getAllByName(String name) {
        int userId = ADMIN_ID;
        LOG.info("get patients by name={} for user {}", name, userId);
        return service.getAllByName(name, userId);
    }

    public Collection<Patient> getFilteredAll(LocalDate startDate, LocalDate endDate) {
        int userId = ADMIN_ID;
        LOG.info("get patients between {} and {} for user {}", startDate, endDate, userId);
        return service.getFilteredAll(startDate, endDate, userId);

    }

    public void update(Patient patient, int id) {
        patient.setThisId(id);
        int userId = ADMIN_ID;
        LOG.info("update patient {} for user {}", patient.getName(), userId);
        service.update(patient, userId);
    }
}
