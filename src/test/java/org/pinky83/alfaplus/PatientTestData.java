package org.pinky83.alfaplus;

import org.pinky83.alfaplus.matcher.ModelMatcher;
import org.pinky83.alfaplus.model.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Дмитрий on 23.12.2016.
 *
 */
public class PatientTestData {
    public static final ModelMatcher<Patient> MATCHER = new ModelMatcher<>();

    public static final int FIRST_PATIENT_ID = 4;
    public static final int LAST_PATIENT_ID = 24633;

    public static final Patient PATIENT1 = new Patient(FIRST_PATIENT_ID, "НЕМЕНКО Т В", LocalDate.of(1966, Month.JANUARY, 1), null, 1, "Недригайлів\r\n" + "вул Кірова 74");
    public static final Patient PATIENT2 = new Patient(5048, "КОЗЛОВСЬКА НАДІЯ ІВАН ", LocalDate.of(1972, Month.JANUARY, 1), null, 1, "вільшанва вул першотравнева 19");
    public static final Patient PATIENT3 = new Patient(13433, "КУРБАНОВ ОЛЕКСАНДР ОВЕЗМУРАТОВИЧ ", LocalDate.of(1970, Month.JANUARY, 1), null, 0, "недригайлів вул горького 72");
    public static final Patient PATIENT4 = new Patient(16969, "ВИННИК ОЛЕКСАНДР МИКОЛАЙОВИЧ ", LocalDate.of(1964, Month.JANUARY, 1), null, 0, "вільшана вул роменська 52");
    public static final Patient PATIENT5 = new Patient(21586, "РОГУЛЯ ГАЛИНА ІВАНІВНА", LocalDate.of(1952, Month.JANUARY, 1), null, 1, "леніна 61");
    public static final Patient PATIENT6 = new Patient(23973, "ЯРОВЕНКО В М ", LocalDate.of(1987, Month.JANUARY, 1), null, 0, "ітк");
    public static final Patient PATIENT7 = new Patient(LAST_PATIENT_ID, "БАЛІЙ РАЇСА ГРИГ", LocalDate.of(1950, Month.JANUARY, 1), null, 0, "вехове");

    public static final List<Patient> PATIENTS = Arrays.asList(PATIENT7, PATIENT6, PATIENT5, PATIENT4, PATIENT3, PATIENT2, PATIENT1);

    public static Patient getCreated() {
        return new Patient("ТЕСТОВЫЙ ПАЦИЕНТ", LocalDate.of(1964, Month.JANUARY, 1), LocalTime.of(0,0), 0, "тестовый адрес");
    }

    public static Patient getUpdated() {
        return new Patient(FIRST_PATIENT_ID+1, "Обновленный пациент", LocalDate.of(1966, Month.JANUARY, 1), LocalTime.of(0,0), 1, "Обновленный адрес");
    }
}
