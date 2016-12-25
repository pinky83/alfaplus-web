package org.pinky83.alfaplus.mock;

import org.pinky83.alfaplus.model.Role;
import org.pinky83.alfaplus.model.User;
import org.pinky83.alfaplus.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Дмитрий on 10.12.2016./
 */
@Repository
public class MockUserRepository implements UserRepository{
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Logger LOG = LoggerFactory.getLogger(MockUserRepository.class);

    {
        List<User> users = Arrays.asList(
                new User(counter.incrementAndGet(), "Федя Николаев",
                        "soto12@mail.ru", "torba856dr", Role.ROLE_DOCTOR),
                new User(counter.incrementAndGet(), "Иван Матюшко",
                        "javauser@hotmail.com", "djnvgj394gr", Role.ROLE_GUEST),
                new User(counter.incrementAndGet(), "Маргарита Строганова",
                        "margo17@gmail.com", "kiso4ka2345", Role.ROLE_ADMIN),
                new User(counter.incrementAndGet(), "Иван Сотников",
                        "vanya1989@ya.ru", "rtl23sderto", Role.ROLE_GUEST)
        );

        users.forEach(this::save);
    }

    @Override
    public User save(User user) {
        if(user.isNew())user.setId(counter.incrementAndGet());
        LOG.info("save " + user);
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean delete(int id) {
       if(repository.containsKey(id)){
            LOG.info("delete " + id);
           repository.remove(id);
           return true;
        }
        return false;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.containsKey(id) ? repository.get(id) : null;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        Optional<User> matchingUsers = repository.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
        return matchingUsers.orElse(null);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        if(repository.values().isEmpty())return Collections.emptyList();
        List<User> result = new ArrayList<>();
        result.addAll(repository.values());
        result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return result;
    }
}
