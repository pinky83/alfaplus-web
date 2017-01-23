package org.pinky83.alfaplus.web.image;

import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Дмитрий on 16.01.2017.
 *
 */
@RestController
@RequestMapping(value = ImageRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageRestController extends AbstractImageController{
    static final String REST_URL = "/rest/images";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleException(NotFoundException ex) throws IOException{
        //response.sendError(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping("/{id}")
    public Image get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    public List<Image> getAll() {return super.getAll();}

    @GetMapping("/paged")
    public Page<Image> getAllPaged() {
        return super.getAllPaged();
    }

    @GetMapping("/full")
    public List<Image> getAllWithLazyFields() {
        return super.getAllWithLazyFields();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {super.delete(id); }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Image image, @PathVariable int id) {
        super.update(image, id);
    }

    @PutMapping(value = "/add/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Image create(@RequestBody Image image) {
        return super.create(image);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> createWithLocation(@RequestBody Image image) {
        Image created = super.create(image);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/full/{id}")
    public Image getWithLazyFields(@PathVariable int id) {
        return super.getWithLazyFields(id);
    }

    @GetMapping("/last")
    public List<Image> getLastDay() {
        return super.getLastDay();
    }

    @GetMapping("/byDate/{date}")
    public List<Image> getAllByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return super.getAllByDate(date);
    }

    @GetMapping("/next/{id}")
    public List<Image> getNextPage(@PathVariable("id") int lastId) {
        return super.getNextPage(lastId);
    }

    @GetMapping("/previous/{id}")
    public List<Image> getPreviousPage(@PathVariable("id") int firstId) {return super.getPreviousPage(firstId);
    }


}
