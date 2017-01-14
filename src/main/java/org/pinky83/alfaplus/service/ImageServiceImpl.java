package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.repository.ImageRepository;
import org.pinky83.alfaplus.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by Дмитрий on 07.01.2017.
 *
 */
@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository repository;

    @Override
    public List<Image> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Page<Image> getAll(Pageable pageable, int userId) {
        return repository.getAll(pageable, userId);
    }

    @Override
    public List<Image> getAllWithLazyFields(Collection<Image> source, int userId) {
        return repository.getAllWithLazyFields(source, userId);
    }

    @Override
    public List<Image> getAllByDate(LocalDate date, int userId) {
        return repository.getAllByDate(date, userId);
    }

    @Override
    public Image getById(int id, int userId) {
        return ExceptionUtil.checkNotFoundWithId(repository.getById(id, userId), id);
    }

    @Override
    public Image getByIdWithLazyFields(int id, int userId) {
        return ExceptionUtil.checkNotFoundWithId(repository.getByIdWithLazyFields(id, userId), id);
    }

    @Override
    public List<Image> getLastDay(int userId) {
        return repository.getLastDay(userId);
    }

    @Override
    public Page<Image> getPreviousPage(Pageable pageable, int startId, int userId) {
        return repository.getPreviousPage(pageable, startId, userId);
    }

    @Override
    public Page<Image> getNextPage(Pageable pageable, int endId, int userId) {
        return repository.getNextPage(pageable, endId, userId);
    }

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Image create(Image image, int userId) {
        return repository.save(image, userId);
    }

    @Override
    public void update(Image newT, int userId) {
        repository.save(newT, userId);
    }
}
