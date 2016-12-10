package org.pinky83.alfaplus.model;

/**
 * Created by Дмитрий on 10.12.2016./
 */
public class NamedEntity extends BaseEntity{
    protected String name;

    public NamedEntity() {
    }

    protected NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;


    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
