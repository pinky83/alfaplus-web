package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.repository.ImageRepository;
import org.pinky83.alfaplus.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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
@Transactional(readOnly = true)
public class DataJpaImageRepository implements ImageRepository{
    @Autowired
    private CrudImageRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Image save(Image image, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID);
        if(!image.isNew() && getById(image.getId(), userId) == null) {
            return null;
        }
        return repository.save(image);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID);
        if(repository.delete(id)!=0){
            entityManager.createQuery("DELETE FROM Series s WHERE s.id = :id").setParameter("id", id).executeUpdate();
            entityManager.createQuery("DELETE FROM Study st WHERE st.id = :id").setParameter("id", id).executeUpdate();
            return true;
        }
        return false;
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
        source.parallelStream().forEach(image -> {
            dest.add(repository.getByIdWithLazyFields(image.getId()));
        });
        return dest;
    }

    @Override
    public List<Image> getAllByDate(LocalDate date, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        return repository.findByImageDateBetween(date.atStartOfDay(), date.atTime(23,59,59));
    }

    @Override
    public List<Image> getLastDay(int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        return getAllByDate(repository.findTOPByImageDate().getImageDate().toLocalDate(),userId);
    }

    @Override
    public Page<Image> getPreviousPage(Pageable pageable, int startId, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        return repository.findByIdIsLessThanOrderByIdDesc(pageable, startId);
    }

    @Override
    public Page<Image> getNextPage(Pageable pageable, int endId, int userId) {
        ExceptionUtil.checkUserId(userId, ADMIN_ID, DOCTOR_ID);
        return repository.findByIdIsGreaterThanOrderByIdAsc(pageable, endId);
    }
}
