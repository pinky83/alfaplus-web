package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Дмитрий on 17.12.2016./
 */
@Repository
public class DataJpaPatientRepository implements PatientRepository{
    @Autowired
    private CrudPatientRepository repository;

    @Override
    public Patient save(Patient patient, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Patient get(int id, int userId) {
        return null;
    }

    @Override
    //TODO need to verify userId
    public Patient getById(int id, int userId) {
        return repository.getById(id);
    }

    @Override
    public Collection<Patient> getAll(int userId) {
        return repository.getAll();
    }

    public Collection<Patient> getAllByName(String name) {
        return repository.getAllByName(name);
    }


    @Override
    public Collection<Patient> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
