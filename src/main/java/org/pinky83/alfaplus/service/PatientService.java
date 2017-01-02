package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.util.exception.AccessViolationException;
import org.pinky83.alfaplus.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface PatientService extends GenericService<Patient> {
    @Override
    Collection<Patient> getAll(int userId) throws AccessViolationException;

    Collection<Patient> getFilteredAll(LocalDate startTime, LocalDate endTime,
                                       int userId) throws AccessViolationException;

    @Override
    Patient getById(int id, int userId) throws NotFoundException, AccessViolationException;

    Collection<Patient> getAllByName(String name, int userId) throws AccessViolationException;

    @Override
    void delete(int id, int userId) throws NotFoundException, AccessViolationException;

    @Override
    Patient create(Patient patient, int userId) throws AccessViolationException;

    void update(Patient newT, int userId) throws NotFoundException, AccessViolationException;
}
