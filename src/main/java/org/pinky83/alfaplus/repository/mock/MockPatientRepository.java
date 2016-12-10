package org.pinky83.alfaplus.repository.mock;

import org.pinky83.alfaplus.model.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Дмитрий on 10.12.2016./
 */
public class MockPatientRepository {
    private static AtomicInteger counter;
    //TODO need to fix static mock repository and inject it via spring
    private static ConcurrentHashMap<Integer, Patient> mockRepository;

    private static List<Patient> firstStage = Arrays.asList(
            new Patient("Масалитин Иван Иванович", LocalDate.of(1983, Month.APRIL, 21), LocalTime.of(12,34), true, "Патологических изменений не выявлено."),
            new Patient("Коваленко Вячеслав Михайлович", LocalDate.of(1983, Month.JANUARY, 18), LocalTime.of(8,15), true, "Сердце асширено влево."),
            new Patient("Песоцкая Мария Николаевна", LocalDate.of(1982, Month.AUGUST, 5), LocalTime.of(15,54), false, "Легочный рисунок усилен."),
            new Patient("Суховей Алексей Анатольевич", LocalDate.of(1985, Month.MARCH, 14), LocalTime.of(2,29), true, "Патологических изменений не выявлено."),
            new Patient("Иченская Оксана Сергеевна", LocalDate.of(1980, Month.FEBRUARY, 26), LocalTime.of(7,20), false, "ОГК."),
            new Patient("Чуба Людмила Игоревна", LocalDate.of(1983, Month.JUNE, 9), LocalTime.of(19,45), false, "Высокое расположение правого купола диафрагмы."),
            new Patient("Шломенко Александр Иванович", LocalDate.of(1984, Month.JULY, 13), LocalTime.of(10,12), true, "Lob. v. Azygos.")
    );

}
