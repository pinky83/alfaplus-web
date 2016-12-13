package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Patient;

import java.util.Collection;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface PatientService extends GenericService<Patient> {
    @Override
    Collection<Patient> getAll(int userId);

    @Override
    Patient getById(int id, int userId);

    @Override
    void delete(int id, int userId);

    @Override
    void create(Patient patient, int userId);

    @Override
    void update(int id, Patient newT, int userId);
}
