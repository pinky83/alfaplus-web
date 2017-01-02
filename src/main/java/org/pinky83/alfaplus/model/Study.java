package org.pinky83.alfaplus.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Дмитрий on 29.11.2016./
 */
@Entity
@Table(name = "XSTUDY")
public class Study extends BaseEntity{
    @Id
    @SequenceGenerator(name = "entity1Seq3", sequenceName="AUTOSTUDYIDGEN", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity1Seq3")
    @Access(value = AccessType.PROPERTY)
    @Column(name = "AUTOSTUDYID")
    private Integer id;

    @Column(name = "STUDY_DATE")
    private LocalDateTime studyDate;

    @Column(name = "PATIENT_AGE")
    private int age;

    @Column(name = "ADDMITTING_DIAGNOSES")
    private String diagnoses;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "study")
    private Image image;

    public Study() {
    }

    public Study(Integer id, LocalDateTime studyDate, int age, String diagnoses, Image image) {
        super(id);
        this.studyDate = studyDate;
        this.age = age;
        this.diagnoses = diagnoses;
        this.image = image;
    }

    public LocalDateTime getStudyDate() {
        return studyDate;
    }

    public int getAge() {
        return age;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public Image getImage() {
        return image;
    }

    public void setStudyDate(LocalDateTime studyDate) {
        this.studyDate = studyDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
