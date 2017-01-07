package org.pinky83.alfaplus.repository;

import org.pinky83.alfaplus.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * Created by Дмитрий on 07.01.2017.
 *
 */
public interface ImageRepository {
    // null if updated patient do not belong to userId
    Image save(Image image, int userId);

    // false if patient do not belong to admin userId
    boolean delete(int id, int userId);

    Image getById(int id, int userId);

    Image getByIdWithLazyFields (int id, int userId);

    // ORDERED dateTime// access violation exception for guests
    List<Image> getAll(int userId);

    Page<Image> getAll(Pageable pageable, int userId);

    List<Image> getAllWithLazyFields(Collection<Image> source, int userId);
}
