package test.docker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.docker.entity.Person;
import test.docker.service.PersonService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/getById")
    public ResponseEntity<Person> findById(@RequestParam UUID id) {
        log.info("Пришел запрос на получение человека с идентификатором {}", id);
        Person found = service.findPersonById(id);
        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam UUID id) {
        log.info("Пришел запрос на удаление человека с идентификатором {}", id);
        service.deletePerson(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Person person) {
        log.info("Пришел запрос на сохранение человека {}", person);
        service.savePerson(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Person>> findAll() {
        log.info("Пришел запрос на получение всех человек");
        List<Person> persons = service.findAll();
        return ResponseEntity.ok(persons);
    }
}
