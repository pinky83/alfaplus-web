package org.pinky83.alfaplus.web.patient;

import org.pinky83.alfaplus.model.Patient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Дмитрий on 18.01.2017.
 *
 */
@RestController
@RequestMapping(value = PatientRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientRestController extends AbstractPatientController{
    static final String REST_URL = "/rest/patients";

    @GetMapping("/{id}")
    public Patient get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("/full/{id}")
    public Patient getWithImages(@PathVariable("id") int id) {
        return super.getWithImages(id);
    }

    @GetMapping
    public Collection<Patient> getAll() {
        return super.getAll();
    }

    @GetMapping("/full/?source")
    public Collection<Patient> getAllWithImages(@RequestParam("source") Collection<Patient> source) {
        return super.getAllWithImages(source);
    }

    @GetMapping("/byName/{name}")
    public Collection<Patient> getAllByName(@PathVariable("name") String name) {
        return super.getAllByName(name);
    }

    @GetMapping("/getBetween")
    public Collection<Patient> getFilteredAll(@RequestParam(value = "date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(value = "date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return super.getFilteredAll(startDate, endDate);
    }

    @PutMapping(value = "/add/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Patient create(@RequestBody Patient patient) {
        return super.create(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    //TODO need to refactor method, patient id not used now
    public void update(@RequestBody Patient patient, @PathVariable("id") int id) {
        super.update(patient);
    }
}
