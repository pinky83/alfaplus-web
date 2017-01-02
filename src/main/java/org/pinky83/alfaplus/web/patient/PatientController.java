package org.pinky83.alfaplus.web.patient;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.model.User;
import org.pinky83.alfaplus.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Дмитрий on 15.12.2016./
 */
@Controller
public class PatientController {
    private final Logger LOG = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService service;

    public Patient create(Patient meal, User user) {
        LOG.info("create " + meal + " : " + user);
        return service.create(meal, user.getId());
    }

    public void delete(int id, int userId) {
        service.delete(id, userId);
    }

    public Patient get(int id, int userId) {
        return service.getById(id, userId);
    }

    public Collection<Patient> getAll(int userId) {
        LOG.info("getAll");
        return service.getAll(userId);
    }

    public Collection<Patient> getFilteredAll(int userId, LocalDate startDate, LocalDate endDate) {
        return service.getFilteredAll(startDate, endDate, userId);

    }

    public void update(Patient patient, User user) {
        service.update(patient, user.getId());
    }
}
