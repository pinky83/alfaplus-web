package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.repository.ImageRepository;
import org.pinky83.alfaplus.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.pinky83.alfaplus.AuthorizedUser.ADMIN_ID;
import static org.pinky83.alfaplus.AuthorizedUser.DOCTOR_ID;
import static org.pinky83.alfaplus.AuthorizedUser.GUEST_ID;

/**
 * Created by Дмитрий on 07.01.2017.
 *
 */
@Repository
public class DataJpaImageRepository implements ImageRepository{
    //TODO need to finish before commit
    @Autowired
    private CrudImageRepository repository;

    @Override
    public Image save(Image image, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID);
        if(!image.isNew() && getById(image.getId(), userId) == null) {
            return null;
        }
        return repository.save(image);
    }

    @Override
    public boolean delete(int id, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID);
        return repository.delete(id)!=0;
    }

    @Override
    public Image getById(int id, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID, GUEST_ID);
        return repository.getById(id);
    }

    @Override
    public Image getByIdWithLazyFields(int id, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID, GUEST_ID);
        return repository.getByIdWithLazyFields(id);
    }

    @Override
    public Page<Image> getAll(Pageable pageable, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        return repository.findAll(pageable);
    }

    @Override
    public List<Image> getAll(int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        return repository.findAll();
    }

    @Override
    public List<Image> getAllWithLazyFields(Collection<Image> source, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        List<Image> dest = new ArrayList<>();
        source.stream().forEach(image -> {
            dest.add(repository.getByIdWithLazyFields(image.getId()));
        });
        return dest;
    }
}
