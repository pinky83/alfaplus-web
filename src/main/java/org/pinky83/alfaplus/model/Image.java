package org.pinky83.alfaplus.model;


import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Дмитрий on 29.11.2016./
 */
@Entity
@Table(name = "XIMAGE")
@Access(AccessType.FIELD)
public class Image extends BaseEntity {
    @Id
    @SequenceGenerator(name = "entity1Seq2", sequenceName = "AUTOIMAGEIDGEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity1Seq2")
    @Column(name = "AUTOIMAGEID")
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "SERIES_NO")
    private Series series;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "PATIENT_NO")
    private Patient patient;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
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
        if(super.getId()!=null)this.id = super.getId();
        this.series = series;
        this.patient = patient;
        this.study = study;
        this.imageDate = imageDate;
        this.description = description;
        this.fileName = fileName;
    }

    public Image(Series series, Patient patient, Study study, LocalDateTime imageDate, String description, String fileName) {
        this(null, series, patient, study, imageDate, description, fileName);
    }

    @Override
    public Integer getId() {
        return this.id;
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

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageDate=" + imageDate +
                ", patient='" + patient.getName() + "'" +
                ", description='" + description  + "'" +
                ", address='" + patient.getComments()  + "'" +
                ", fileName='" + fileName  + "'" +
                '}';
    }
}
