package org.pinky83.alfaplus.model;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.pinky83.alfaplus.service.PatientServiceMock.counter;

/**
 * Created by Дмитрий on 29.11.2016./
 */
public class Patient {
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private LocalTime birthTime;
    private boolean sex;
    private String comments;

    public Patient() {
    }

    public Patient(String name, LocalDate birthDate, LocalTime birthTime, boolean sex, String comments) {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.sex = sex;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalTime getBirthTime() {
        return birthTime;
    }

    public boolean isSex() {
        return sex;
    }

    public String getComments() {
        return comments;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthTime(LocalTime birthTime) {
        this.birthTime = birthTime;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
