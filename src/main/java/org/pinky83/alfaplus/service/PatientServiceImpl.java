package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.repository.PatientRepository;
import org.pinky83.alfaplus.util.exception.AccessViolationException;
import org.pinky83.alfaplus.util.exception.ExceptionUtil;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Дмитрий on 30.11.2016./
 */
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository repository;

    @Cacheable("patients")
    @Override
    public Collection<Patient> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Collection<Patient> getAllWithImages(Collection<Patient> source, int userId) throws AccessViolationException {
        return repository.getAllWithImages(source, userId);
    }

    @Override
    public Collection<Patient> getFilteredAll(LocalDate startTime, LocalDate endTime, int userId) {
        return repository.getBetween(startTime, endTime, userId);
    }

    @Override
    public Patient getById(int id, int userId) {
        return ExceptionUtil.checkNotFoundWithId(repository.getById(id, userId), id);
    }

    @Override
    public Patient getByIdWithImages(int id, int userId) throws NotFoundException, AccessViolationException {
        return ExceptionUtil.checkNotFoundWithId(repository.getByIdWithImages(id, userId), id);
    }

    @Override
    @Cacheable("patients")
    public Collection<Patient> getAllByName(String name, int userId) {
        return repository.getAllByName(name, userId);
    }

    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public Patient create(Patient patient, int userId) {
        return repository.save(patient, userId);
    }

    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public void update(Patient newT, int userId) {
        repository.save(newT, userId);
    }

    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public void evictCache() {

    }
}
