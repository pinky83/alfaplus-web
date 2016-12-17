package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;


/**
 * Created by Дмитрий on 17.12.2016./
 */
public interface CrudPatientRepository extends JpaRepository<Patient, Integer> {
//TODO Need to complete data-jpa repositories
    Patient save(Patient patient, int userId);

    boolean delete(int id, int userId);

    Patient get(int id, int userId);

    Collection<Patient> getAll(int userId);

    Collection<Patient> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
