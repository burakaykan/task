package me.burakaykan.task.controller;

import me.burakaykan.task.model.Person;
import me.burakaykan.task.model.StatDto;
import me.burakaykan.task.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Person>> listAll() {
        return service.listAll();
    }
    @GetMapping("/stats")
    public ResponseEntity<Map<String, StatDto>> listTotalUniqueEmails() {
        return service.listTotalUniqueEmails();
    }
}
