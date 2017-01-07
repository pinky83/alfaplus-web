package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.repository.PatientRepository;
import org.pinky83.alfaplus.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;
import static org.pinky83.alfaplus.AuthorizedUser.DOCTOR_ID;
import static org.pinky83.alfaplus.AuthorizedUser.GUEST_ID;

/**
 * Created by Дмитрий on 17.12.2016./
 */
@Repository
public class DataJpaPatientRepository implements PatientRepository{
    @Autowired
    private CrudPatientRepository repository;

    @Override
    @Transactional
    public Patient save(Patient patient, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID);
        if(!patient.isNew() && getById(patient.getId(), userId) == null) {
            return null;
        }
        return repository.save(patient);
    }

    @Override
    public boolean delete(int id, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID);
        return repository.delete(id)!=0;}

    @Override
    public Patient get(int id, int userId) {
        return null;
    }

    @Override

    public Patient getById(int id, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID, GUEST_ID);
        return repository.getById(id);
    }

    @Override
    public Patient getByIdWithImages(int id, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID, GUEST_ID);
        return repository.getByIdWithImages(id);
    }

    @Override
    public Collection<Patient> getAll(int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        return repository.getAll();
    }

    @Override
    @Transactional
    public Collection<Patient> getAllWithImages(Collection<Patient> source, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        List<Patient> dest = new ArrayList<>();
       source.stream().forEach(patient -> {
           dest.add(repository.getByIdWithImages(patient.getId()));
       });
        return dest;
    }

    public Collection<Patient> getAllByName(String name, int userId) {
        ExceptionUtil.checkUserId(userId,ADMIN_ID, DOCTOR_ID);
        return repository.findByNameContaining(name.toUpperCase());
    }

    @Override
    public Collection<Patient> getBetween(LocalDate startDate, LocalDate endDate, int userId) {
        ExceptionUtil.checkUserId(userId,ADMIN_ID, DOCTOR_ID);
        return repository.birthDateBetween(startDate, endDate);
    }
}
