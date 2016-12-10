package org.pinky83.alfaplus.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Дмитрий on 29.11.2016./
 */
public class Patient extends NamedEntity{
    private LocalDate birthDate;
    private LocalTime birthTime;
    private boolean sex;
    private String comments;

    public Patient() {
    }

    public Patient(Integer id, String name, LocalDate birthDate, LocalTime birthTime, boolean sex, String comments) {
        super(id, name);
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.sex = sex;
        this.comments = comments;
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
