package org.pinky83.alfaplus.web.patient;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleException(NotFoundException ex) throws IOException {
        //response.sendError(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping("/{id}")
    public Patient get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/full/{id}")
    public Patient getWithImages(@PathVariable int id) {
        return super.getWithImages(id);
    }

    @GetMapping
    public Collection<Patient> getAll() {
        return super.getAll();
    }

    @GetMapping("/full/?source")
    public Collection<Patient> getAllWithImages(@RequestParam Collection<Patient> source) {
        return super.getAllWithImages(source);
    }

    @GetMapping("/byName/{name}")
    public Collection<Patient> getAllByName(@PathVariable String name) {
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> createWithLocation(@RequestBody Patient patient) {
        Patient created = super.create(patient);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Patient patient, @PathVariable int id) {
        super.update(patient, id);
    }
}
