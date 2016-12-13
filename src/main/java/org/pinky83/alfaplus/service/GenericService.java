package org.pinky83.alfaplus.service;

import java.util.Collection;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface GenericService<T> {
    public Collection<T> getAll(int userId);
    public T getById(int id, int userId);
    public void delete(int id, int userId);
    public void create (T t, int userId);
    public void update (int id, T newT, int userId);
}
