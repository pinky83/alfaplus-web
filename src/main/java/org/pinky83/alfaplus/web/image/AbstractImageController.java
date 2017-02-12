package org.pinky83.alfaplus.web.image;

import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.service.ImageService;
import org.pinky83.alfaplus.service.PatientService;
import org.pinky83.alfaplus.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;

/**
 * Created by Дмитрий on 16.01.2017.
 *
 */
public abstract class AbstractImageController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractImageController.class);

    @Autowired
    private ImageService service;

    @Autowired
    private PatientService patientService;

    public Image get(int id) {
    int userId = ADMIN_ID;
        LOG.info("get image {} for User {}", id, userId);
        Image image = service.getById(id, userId);
        if (image.getDescription() == null) image.setDescription(" ");
        return image;
    }

    public Image getWithLazyFields(int id) {
        int userId = ADMIN_ID;
        LOG.info("get image {} with lazy fields for User {}", id, userId);
        return service.getByIdWithLazyFields(id, userId);
    }

    public void delete(int id) {
        int userId = ADMIN_ID;
        LOG.info("delete image {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public Page<Image> getAllPaged() {
        int userId = ADMIN_ID;
        List<Image> source = service.getAll(userId);
        LOG.info(" getAll in pages for User {}", userId);
        return service.getAll(new PageRequest(source.size()/70, 70), userId);
    }

    public List<Image> getAll() {
        int userId = ADMIN_ID;
        LOG.info(" getAll for User {}", userId);
        return service.getAll(userId);
    }

    public List<Image> getAllWithLazyFields() {
        int userId = ADMIN_ID;
        List<Image> source = service.getAll(userId);
        LOG.info(" getAll with lazy fields for User {}", userId);
        return service.getAllWithLazyFields(source, userId);
    }

    public void update(Image image, int id) {
        image.setThisId(id);
        int userId = ADMIN_ID;
        LOG.info("update {} for User {}", image, userId);
        service.update(image, userId);
    }

    public Image create(Image image) {
        image.setId(null);
        int userId = ADMIN_ID;
        LOG.info("create {} for User {}", image, userId);
        return service.create(image, userId);
    }

    public List<Image> getLastDay() {
        int userId = ADMIN_ID;
        LOG.info(" getLastDay for User {}", userId);
        List<Image> images = service.getLastDay(userId);
        images.forEach(image -> { if (image.getDescription() == null) image.setDescription(" ");});
        return images;
    }

    public List<Image> getAllByDate(LocalDate date) {
        int userId = ADMIN_ID;
        LOG.info(" getAll by date {} for User {}", date, userId);
        List<Image> images = service.getAllByDate(date != null ? date : TimeUtil.MIN_DATE, userId);
        images.forEach(image -> { if (image.getDescription() == null) image.setDescription(" ");});
        return images;
    }

    public List<Image> getAllByPatient(int id) {
        int userId = ADMIN_ID;
        LOG.info(" getAll by Patient {} for User {}", id, userId);
        List<Image> images = patientService.getByIdWithImages(id, userId).getImages();
        images.forEach(image -> { if (image.getDescription() == null) image.setDescription(" ");});
        return images;
    }

    public List<Image> getNextPage(int lastId) {
        int userId = ADMIN_ID;
        LOG.info("getNextPage starts after id={} for User {}", lastId, userId);
        List<Image> images = service.getNextPage(new PageRequest(0, 20), lastId, userId).getContent();
        images.forEach(image -> { if (image.getDescription() == null) image.setDescription(" ");});
        return images;
    }

    public List<Image> getPreviousPage(int firstId) {
        int userId = ADMIN_ID;
        LOG.info("getNextPage ends before id={} for User {}", firstId, userId);
        List<Image> source = service.getPreviousPage(new PageRequest(0, 20), firstId, userId).getContent();
        List<Image> dest = new ArrayList<>();
        source.forEach(dest::add);
        Collections.reverse(dest);
        dest.forEach(image -> { if (image.getDescription() == null) image.setDescription(" ");});
        return dest;
    }
}
