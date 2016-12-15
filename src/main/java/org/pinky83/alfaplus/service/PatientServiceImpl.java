package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Дмитрий on 30.11.2016./
 */
@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository repository;
    @Override
    public Collection<Patient> getAll(int userId) {return repository.getAll(userId);
    }

    @Override
    public Collection<Patient> getFilteredAll(LocalDateTime startTime, LocalDateTime endTime, int userId) {
        return repository.getBetween(startTime, endTime, userId);
    }

    @Override
    public Patient getById(int id, int userId) {
        for (Patient p : repository.getAll(userId))
            if(Objects.equals(p.getId(), id)) return p;
        return null;
    }

    @Override
    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    @Override
    public Patient create(Patient patient, int userId) {
      return   repository.save(patient, userId);
    }

    @Override
    public void update(Patient newT, int userId) {
        repository.save(newT, userId);
    }
}
