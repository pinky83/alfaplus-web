package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.repository.PatientRepository;
import org.pinky83.alfaplus.repository.mock.MockPatientRepository;

import java.util.*;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public class PatientServiceImpl implements PatientService{

    private PatientRepository repository = new MockPatientRepository();
//TODO design access restrictions by userId checking (use Roles Set). In base we do not have
//TODO user field, so need to control this in service layer.
    @Override
    public Collection<Patient> getAll(int userId) {return repository.getAll(userId);
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
    public void create(Patient patient, int userId) {
        repository.save(patient, userId);
    }

    @Override
    public void update(int id, Patient newT, int userId) {
        repository.save(newT, userId);
    }
}
