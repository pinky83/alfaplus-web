package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Patient;

import java.util.*;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public class PatientServiceImpl implements PatientService{
//TODO design access restrictions by userId checking (use Roles Set). In base we do not have
// user field, so need to control this on service layer.
    @Override
    public List<Patient> getAll(int userId) {return repository.getAll();
    }

    @Override
    public Patient getById(int id, int userId) {
        for (Patient p : repository.getAll())
            if(Objects.equals(p.getId(), id)) return p;
        return null;
    }

    @Override
    public void delete(int id, int userId) {
        repository.delete(patient);
    }

    @Override
    public void save(Patient patient, int userId) {
        repository.save(patient, userId);
    }

    @Override
    public void update(Patient newT, int userId) {
        repository.save(newT);
    }
}
