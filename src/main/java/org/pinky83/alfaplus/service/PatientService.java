package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface PatientService extends GenericService<Patient> {
    @Override
    Collection<Patient> getAll(int userId);

    Collection<Patient> getFilteredAll(LocalDateTime startTime, LocalDateTime endTime,
                                       int userId);

    @Override
    Patient getById(int id, int userId) throws NotFoundException;

    @Override
    void delete(int id, int userId) throws NotFoundException;

    @Override
    Patient create(Patient patient, int userId);

    void update(Patient newT, int userId) throws NotFoundException;
}
