package org.pinky83.alfaplus.model;


import java.time.LocalDateTime;

/**
 * Created by Дмитрий on 29.11.2016./
 */
public class Image {
    private Integer id;
    private Integer seriesId;
    private Integer patientId;
    private Integer studyId;
    private LocalDateTime imageDate;
    private String description; //IMAGE_COMMENTS
    private String fileName;

    public Image() {
    }

    public Image(Integer id, Integer seriesId, Integer patientId, Integer studyId, LocalDateTime imageDate, String description, String fileName) {
        this.id = id;
        this.seriesId = seriesId;
        this.patientId = patientId;
        this.studyId = studyId;
        this.imageDate = imageDate;
        this.description = description;
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getStudyId() {
        return studyId;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setStudyId(Integer studyId) {
        this.studyId = studyId;
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
