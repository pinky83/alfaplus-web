package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.repository.PatientRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Дмитрий on 17.12.2016./
 */
@Repository
@Transactional(readOnly = true)
public class DataJpaPatientRepository implements PatientRepository{
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
    public Collection<Patient> getAll(int userId) {
        return null;
    }

    @Override
    public Collection<Patient> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
