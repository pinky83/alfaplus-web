package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Patient;

import java.util.List;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface PatientService extends GenericService<Patient> {
    @Override
    List<Patient> getAll();

    @Override
    Patient getById(Integer id);

    @Override
    void delete(Patient patient);

    @Override
    void create(Patient patient);

    @Override
    void update(Integer id, Patient newT);
}
