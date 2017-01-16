package org.pinky83.alfaplus.web.image;

import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.service.ImageService;
import org.pinky83.alfaplus.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
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

    public Image get(int id) {
    int userId = ADMIN_ID;
        LOG.info("get image {} for User {}", id, userId);
        return service.getById(id, userId);
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
        image.setId(id);
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
        return service.getLastDay(userId);
    }

    public List<Image> getAllByDate(LocalDate date) {
        int userId = ADMIN_ID;
        LOG.info(" getAll by date {} for User {}", date, userId);
        return service.getAllByDate(date != null ? date : TimeUtil.MIN_DATE, userId);
    }

    public List<Image> getNextPage(int lastId) {
        int userId = ADMIN_ID;
        LOG.info("getNextPage starts after id={} for User {}", lastId, userId);
        return service.getNextPage(new PageRequest(0, 70), lastId, userId).getContent();
    }

    public List<Image> getPreviousPage(int firstId) {
        int userId = ADMIN_ID;
        LOG.info("getNextPage ends before id={} for User {}", firstId, userId);
        return service.getPreviousPage(new PageRequest(0, 70), firstId, userId).getContent();
    }
}
