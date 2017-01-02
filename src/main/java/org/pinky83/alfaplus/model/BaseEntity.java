package org.pinky83.alfaplus.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

/**
 * Created by Дмитрий on 10.12.2016.//
 */

@Access(AccessType.FIELD)
public class BaseEntity implements Persistable<Integer> {
    // different on all tables)

    private Integer id;

    public BaseEntity() {
    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew(){
        return this.id==null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
