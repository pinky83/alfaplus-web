package org.pinky83.alfaplus;

import org.pinky83.alfaplus.matcher.ModelMatcher;
import org.pinky83.alfaplus.model.Image;
import org.pinky83.alfaplus.model.Series;
import org.pinky83.alfaplus.model.Study;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.pinky83.alfaplus.PatientTestData.PATIENT1;
import static org.pinky83.alfaplus.PatientTestData.PATIENT8;

/**
 * Created by Дмитрий on 09.01.2017.
 *
 */
public class ImageTestData {
    public static final ModelMatcher<Image> IMAGE_MODEL_MATCHER = new ModelMatcher<>();
    public static final ModelMatcher<Series> SERIES_MODEL_MATCHER = new ModelMatcher<>();
    public static final ModelMatcher<Study> STUDY_MODEL_MATCHER = new ModelMatcher<>();

    public static final int N_OF_ELEMENTS = 61661;
    public static final int SIZE_WITHOUT_LAZY_FIELDS = 13854074;
    public static final int IMAGES_COLLECTION_HASH = 2120565663;

    public static final int FIRST_IMAGE_ID = 3;
    public static final int LAST_IMAGE_ID = 61663;

    public static final Image FIRST_IMAGE = new Image(FIRST_IMAGE_ID + 1, null, PATIENT1, null, LocalDateTime.of(LocalDate.of(2007, Month.FEBRUARY, 16), LocalTime.of(11, 12, 18)), "Патологічних змін не виявлено", "00000004.JPG");
    public static final Image LAST_IMAGE = new Image(LAST_IMAGE_ID, null, PATIENT1, null, LocalDateTime.of(LocalDate.of(2007, Month.FEBRUARY, 16), LocalTime.of(11, 12, 18)), "Патологічних змін не виявлено", "00000004.JPG");

    public static final Image IMAGE1 = new Image(4800, null, PATIENT8, null, LocalDateTime.of(LocalDate.of(2007, Month.NOVEMBER, 15), LocalTime.of(10, 30, 27)), "Патологічних змін не виявлено", "00004800.JPG");
    public static final Image IMAGE2 = new Image(8467, null, PATIENT8, null, LocalDateTime.of(LocalDate.of(2008, Month.JULY, 10), LocalTime.of(12, 24, 29)), null, "00008467.JPG");
    public static final Image IMAGE3 = new Image(24438, null, PATIENT8, null, LocalDateTime.of(LocalDate.of(2011, Month.APRIL, 1), LocalTime.of(13, 24, 53)), "Патологічних змін не виявлено", "00024438.JPG");
    public static final Image IMAGE4 = new Image(25872, null, PATIENT8, null, LocalDateTime.of(LocalDate.of(2011, Month.MAY, 31), LocalTime.of(12, 56, 24)), "Верхушки срезаны.", "00025872.JPG");
    public static final Image IMAGE5 = new Image(42909, null, PATIENT8, null, LocalDateTime.of(LocalDate.of(2013, Month.OCTOBER, 30), LocalTime.of(14, 3, 20)), null, "00042909.JPG");
    public static final Image IMAGE6 = new Image(53402, null, PATIENT8, null, LocalDateTime.of(LocalDate.of(2015, Month.APRIL, 29), LocalTime.of(9, 29, 8)), null, "00053402.JPG");
    public static final Image IMAGE7 = new Image(61396, null, PATIENT8, null, LocalDateTime.of(LocalDate.of(2016, Month.JUNE, 13), LocalTime.of(10, 6, 48)), "Патологічних змін не виявлено", "00061396.JPG");

    public static final Series IMAGE7_SERIES = new Series(IMAGE7.getId(), LocalDateTime.of(LocalDate.of(2016, Month.JUNE, 13), LocalTime.of(10, 6, 48)),null, null, null, IMAGE7);

    public static final Study IMAGE7_STUDY = new Study(IMAGE7.getId(), LocalDateTime.of(LocalDate.of(2016, Month.JUNE, 13), LocalTime.of(10, 6, 48)), "33", null, IMAGE7);

    public static final List<Image> IMAGES = Arrays.asList(IMAGE1, IMAGE2, IMAGE3, IMAGE4, IMAGE5, IMAGE6, IMAGE7);

    public static Image getCreated() {
        return new Image(null, null, PATIENT8, null, LocalDateTime.of(LocalDate.of(2016, Month.JUNE, 13), LocalTime.of(10, 6, 48)), "тестовое описание", "тестовый.JPG");
    }

    public static Image getUpdated() {
        return new Image(FIRST_IMAGE_ID+1, null, PATIENT1, null, LocalDateTime.of(LocalDate.of(2016, Month.JUNE, 13), LocalTime.of(10, 6, 48)), "обновленное описание", "обновленный.JPG");
    }
}
