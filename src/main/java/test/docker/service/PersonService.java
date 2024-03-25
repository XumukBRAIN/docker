package test.docker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import test.docker.entity.Person;
import test.docker.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public void savePerson(Person person) {
        try {
            repository.save(person);
            log.info("Person saved: {}", person);
        } catch (Exception e) {
            log.error("Person not saved: {}", person, e);
            throw new RuntimeException(e);
        }
    }

    public void deletePerson(UUID id) {
        try {
            repository.deleteById(id);
            log.info("Person deleted: {}", id);
        } catch (Exception e) {
            log.error("Person not deleted: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    public Person findPersonById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found: " + id));
    }

    public List<Person> findAll() {
        return repository.findAll();
    }
}
