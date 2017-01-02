package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;


/**
 * Created by Дмитрий on 17.12.2016./
 */
@Transactional(readOnly = true)
public interface CrudPatientRepository extends JpaRepository<Patient, Integer> {

    @Override
    Patient save(Patient patient);

    @Modifying
    @Transactional
    @Query("DELETE FROM Patient p WHERE p.id=:id")
    int delete(@Param("id") int id);

    Collection<Patient> findByNameContaining(@Param("name") String name);

    @Query("SELECT p FROM Patient p")
    Collection<Patient> getAll();

    Patient getById(int id);

    Collection<Patient> birthDateBetween(LocalDate startDate, LocalDate endDate);
}
