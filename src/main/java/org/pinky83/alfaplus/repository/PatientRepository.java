package org.pinky83.alfaplus.repository;

import org.pinky83.alfaplus.model.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Дмитрий on 10.12.2016.//
 */
public interface PatientRepository {
    // null if updated patient do not belong to userId
    Patient save(Patient patient, int userId);

    // false if patient do not belong to admin userId
    boolean delete(int id, int userId);

    // null if patient data - name, sex, birth not correspond to  userId - for guests
    Patient get(int id, int userId);

    Patient getById(int id, int userId);

    // ORDERED dateTime// access violation exception for guests
    Collection<Patient> getAll(int userId);

    Collection<Patient> getAllByName(String name, int userId);

    // ORDERED dateTime//access violation exception for guests
    Collection<Patient> getBetween(LocalDate startDate, LocalDate endDate, int userId);
}
