package org.pinky83.alfaplus.model;


import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Дмитрий on 29.11.2016./
 */
@Entity
@Table(name = "XIMAGE")
public class Image extends BaseEntity {
    @Id
    @SequenceGenerator(name = "entity1Seq2", sequenceName = "AUTOIMAGEIDGEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity1Seq2")
    @Access(value = AccessType.PROPERTY)
    @Column(name = "AUTOIMAGEID")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "SERIES_NO")
    private Series series;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT_NO")
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDY_NO")
    private Study study;

    @Column(name = "IMAGE_DATE")
    private LocalDateTime imageDate;

    @Column(name = "IMAGE_COMMENTS")
    private String description; //IMAGE_COMMENTS

    @Column(name = "IMAGE_FILE_NAME")
    private String fileName;

    public Image() {
    }

    public Image(Integer id, Series series, Patient patient, Study study, LocalDateTime imageDate, String description, String fileName) {
        super(id);
        this.series = series;
        this.patient = patient;
        this.study = study;
        this.imageDate = imageDate;
        this.description = description;
        this.fileName = fileName;
    }

    public Series getSeries() {
        return series;
    }

    public Patient getPatient() {
        return patient;
    }

    public Study getStudy() {
        return study;
    }

    public LocalDateTime getImageDate() {
        return imageDate;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public void setImageDate(LocalDateTime imageDate) {
        this.imageDate = imageDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
