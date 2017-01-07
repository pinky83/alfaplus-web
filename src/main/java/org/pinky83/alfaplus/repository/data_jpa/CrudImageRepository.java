package org.pinky83.alfaplus.repository.data_jpa;

import org.pinky83.alfaplus.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Дмитрий on 07.01.2017.
 *
 */
@Transactional(readOnly = true)
public interface CrudImageRepository extends JpaRepository<Image, Integer> {

    Image getById(int id);

    @Query("SELECT i FROM Image i JOIN FETCH i.patient JOIN FETCH i.series JOIN FETCH i.study WHERE i.id = ?1")
    Image getByIdWithLazyFields(int id);

    @Override
    Image save(Image image);

    @Transactional
    @Modifying
    @Query("DELETE FROM Image i WHERE i.id = ?1")
    int delete(int id);

    @Override
    List<Image> findAll();

    @Override
    Page<Image> findAll(Pageable pageable);
}
