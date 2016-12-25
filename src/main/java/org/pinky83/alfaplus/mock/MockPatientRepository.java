package org.pinky83.alfaplus.mock;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.model.User;
import org.pinky83.alfaplus.repository.PatientRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Дмитрий on 10.12.2016./
 */
@Repository
public class MockPatientRepository implements PatientRepository{
    private AtomicInteger counter = new AtomicInteger(0);
    public Map<Integer, Patient> mockRepository = new ConcurrentHashMap<>();
    private User testUser = new User();
    private Collection<Patient> firstStage = Arrays.asList(
            new Patient("Масалитин Иван Иванович", LocalDate.of(1983, Month.APRIL, 21), LocalTime.of(12,34), 1, "Патологических изменений не выявлено."),
            new Patient("Коваленко Вячеслав Михайлович", LocalDate.of(1983, Month.JANUARY, 18), LocalTime.of(8,15), 1, "Сердце асширено влево."),
            new Patient("Песоцкая Мария Николаевна", LocalDate.of(1982, Month.AUGUST, 5), LocalTime.of(15,54), 0, "Легочный рисунок усилен."),
            new Patient("Суховей Алексей Анатольевич", LocalDate.of(1985, Month.MARCH, 14), LocalTime.of(2,29), 1, "Патологических изменений не выявлено."),
            new Patient("Иченская Оксана Сергеевна", LocalDate.of(1980, Month.FEBRUARY, 26), LocalTime.of(7,20), 0, "ОГК."),
            new Patient("Чуба Людмила Игоревна", LocalDate.of(1983, Month.JUNE, 9), LocalTime.of(19,45), 0, "Высокое расположение правого купола диафрагмы."),
            new Patient("Шломенко Александр Иванович", LocalDate.of(1984, Month.JULY, 13), LocalTime.of(10,12), 1, "Lob. v. Azygos.")
    );
    {
        testUser.setId(1);//we need only id field for tests

        firstStage.forEach(patient -> save(patient, testUser.getId()));
    }
    @Override
    public Patient save(Patient patient, int userId) {
        if(patient.isNew()){
            patient.setId(counter.incrementAndGet());
        }
        if(testUser.getId().equals(userId))mockRepository.put(patient.getId(), patient);
        return patient;
    }

    @Override
    public boolean delete(int id, int userId) {
        if(mockRepository.containsKey(id)) {
            if (userId == testUser.getId()) {
                mockRepository.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Patient get(int id, int userId) {
        if(mockRepository.containsKey(id)){
            if (userId == testUser.getId()) {
                return mockRepository.remove(id);
            }
        }
        return null;
    }

    @Override
    public Patient getById(int id, int userId) {
        return null;
    }

    @Override
    public Collection<Patient> getAll(int userId) {
        return userId == testUser.getId() ? mockRepository.values() : null;
    }

    @Override
    public Collection<Patient> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return userId == testUser.getId() ?
                mockRepository.values().stream().filter(patient -> patient.getBirthDate().isBefore(
                endDate.plusDays(1).toLocalDate())).filter(patient -> patient.getBirthDate()
                .isAfter(startDate.minusDays(1).toLocalDate())).collect(Collectors.toList())
                :
                null;
    }
}
