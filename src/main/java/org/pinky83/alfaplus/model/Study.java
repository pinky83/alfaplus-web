package org.pinky83.alfaplus.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Дмитрий on 29.11.2016./
 */
@Entity
@Table(name = "XSTUDY")
@Access(AccessType.FIELD)
public class Study extends BaseEntity{
    @Id
    @SequenceGenerator(name = "entity1Seq3", sequenceName="AUTOSTUDYIDGEN", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity1Seq3")
    @Column(name = "AUTOSTUDYID")
    private int id;

    @Column(name = "STUDY_DATE")
    private LocalDateTime studyDate;

    @Column(name = "PATIENT_AGE")
    private String age;

    @Column(name = "ADDMITTING_DIAGNOSES")
    private String diagnoses;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "study")
    private Image image;

    public Study() {
    }

    public Study(Integer id, LocalDateTime studyDate, String age, String diagnoses, Image image) {
        super(id);
        if(super.getId()!=null)this.id = super.getId();
        this.studyDate = studyDate;
        this.age = age;
        this.diagnoses = diagnoses;
        this.image = image;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public LocalDateTime getStudyDate() {
        return studyDate;
    }

    public String getAge() {
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

    public void setAge(String age) {
        this.age = age;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", studyDate=" + studyDate +
                ", age=" + age +
                ", diagnoses='" + diagnoses + '\'' +
                '}';
    }
}
