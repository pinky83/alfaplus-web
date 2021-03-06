package org.pinky83.alfaplus.service;

import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.util.exception.AccessViolationException;
import org.pinky83.alfaplus.util.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by Дмитрий on 07.01.2017.
 *
 */
public interface ImageService extends GenericService<Image>{
    @Override
    List<Image> getAll(int userId) throws AccessViolationException;

    Page<Image> getAll(Pageable pageable, int userId) throws AccessViolationException;

    List<Image> getAllWithLazyFields(Collection<Image> source, int userId) throws AccessViolationException;

    List<Image> getAllByDate(LocalDate date, int userId) throws AccessViolationException;

    @Override
    Image getById(int id, int userId) throws NotFoundException, AccessViolationException;

    Image getByIdWithLazyFields(int id, int userId) throws NotFoundException, AccessViolationException;

    List<Image> getLastDay (int userId) throws AccessViolationException;

    Page<Image> getPreviousPage(Pageable pageable, int startId, int userId) throws AccessViolationException;

    Page<Image> getNextPage(Pageable pageable, int endId, int userId) throws AccessViolationException;

    @Override
    void delete(int id, int userId) throws NotFoundException, AccessViolationException;

    @Override
    Image create(Image image, int userId) throws AccessViolationException;

    @Override
    void update(Image newT, int userId) throws NotFoundException, AccessViolationException;
}
