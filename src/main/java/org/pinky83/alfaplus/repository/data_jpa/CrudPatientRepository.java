package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


/**
 * Created by Дмитрий on 17.12.2016./
 */
public interface CrudPatientRepository extends JpaRepository<Patient, Integer> {
//TODO Need to complete data-jpa repositories
    //Patient save(Patient patient, int userId);

    //boolean delete(int id, int userId);

    //Patient get(int id, int userId);
    @Query("SELECT p FROM Patient p WHERE p.name=:name ORDER BY p.birthDate DESC")
    Collection<Patient> getAllByName(@Param("name") String name);
    @Query("SELECT p FROM Patient p")
    Collection<Patient> getAll();

    Patient getById(int id);

    //Collection<Patient> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
