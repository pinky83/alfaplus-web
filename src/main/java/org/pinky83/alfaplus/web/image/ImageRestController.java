package org.pinky83.alfaplus.web.image;

import org.pinky83.alfaplus.model.Image;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Дмитрий on 16.01.2017.
 *
 */
@RestController
@RequestMapping(value = ImageRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageRestController extends AbstractImageController{
    static final String REST_URL = "/rest/images";

    @GetMapping("/{id}")
    public Image get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Image> getAll() {return super.getAll();}
}
