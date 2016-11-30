package org.pinky83.alfaplus.service;

import java.util.List;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public interface GenericService<T> {
    public  List<T>  getAll();
    public T getById(Integer id);
    public void delete(T t);
    public void create (T t);
    public void update (Integer id, T newT);
}
