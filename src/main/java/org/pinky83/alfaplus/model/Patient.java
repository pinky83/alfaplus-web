package org.pinky83.alfaplus.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Дмитрий on 29.11.2016./
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "XPATIENT")
@Access(AccessType.FIELD)
public class Patient extends NamedEntity{
    @Id
    @SequenceGenerator(name = "entity1Seq", sequenceName="AUTOPATIENTIDGEN", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity1Seq")
    @Column(name = "AUTOPATIENTID")
    private int id;

    @Column(name = "PATIENTBIRTHDATE")
    @NotEmpty
    @NotNull
    private LocalDate birthDate;

    @Column(name = "PATIENTBIRTHTIME")
    private LocalTime birthTime;

    @Column(name = "PATIENTSEXINT")
    @NotEmpty
    private int sex;

    @NotNull
    @Column(name = "PATIENTCOMMENTS")
    private String comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Image> images;

    public Patient() {
    }

    public Patient(Integer id, String name, LocalDate birthDate, LocalTime birthTime, int sex, String comments) {
        super(id, name);
        if(super.getId()!=null)this.id = super.getId();
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.sex = sex;
        this.comments = comments;
    }

    public Patient(String name, LocalDate birthDate, LocalTime birthTime, int sex, String comments) {
       this(null, name, birthDate, birthTime, sex, comments);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalTime getBirthTime() {
        return birthTime;
    }

    public int getSex() {
        return sex;
    }

    public String getComments() {
        return comments;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthTime(LocalTime birthTime) {
        this.birthTime = birthTime;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Image> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name=" + name +
                "birthDate=" + birthDate +
                ", birthTime=" + birthTime +
                ", sex=" + sex +
                ", comments='" + comments + '\'' +
                '}';
    }
}
