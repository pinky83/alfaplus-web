package org.pinky83.alfaplus.model;


import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Дмитрий on 29.11.2016./
 */
@Entity
@Table(name = "XSERIES")
public class Series extends BaseEntity{
    @Id
    @SequenceGenerator(name = "entity1Seq1", sequenceName="AUTOSERIESIDGEN", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity1Seq1")
    @Access(value = AccessType.PROPERTY)
    @Column(name = "AUTOSERIESID")
    private Integer id;

    @Column(name = "SERIES_DATE")
    private LocalDateTime seriesDate;

    @Column(name = "OPERATORS_NAME")
    private String operator;

    @Column(name = "BODYPART_EXAMINED")
    private String bodyPart;

    @Column(name = "VIEW_POSITION")
    private String position;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "series")
    private Image image;

    public Series() {
    }

    public Series(Integer id, LocalDateTime seriesDate, String operator, String bodyPart, String position, Image image) {
        super(id);
        this.seriesDate = seriesDate;
        this.operator = operator;
        this.bodyPart = bodyPart;
        this.position = position;
        this.image = image;
    }

    public LocalDateTime getSeriesDate() {
        return seriesDate;
    }

    public String getOperator() {
        return operator;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public String getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
    }

    public void setSeriesDate(LocalDateTime seriesDate) {
        this.seriesDate = seriesDate;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
