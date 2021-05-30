package me.burakaykan.task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.burakaykan.task.model.Person;
import me.burakaykan.task.model.StatDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService {

    private List<Person> personList = new ArrayList<>();

    final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = {"quickstart-events"})
    void listener(String message) {
        try {
            Person incomingPerson = objectMapper.readValue(message, Person.class);
            personList.add(incomingPerson);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    public ResponseEntity<List<Person>> listAll() {
        return new ResponseEntity<>(personList.stream().distinct().collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, StatDto>> listTotalUniqueEmails() {
        List<String> emailList = personList.stream().map(Person::getEmail).distinct().collect(Collectors.toList());
        List<String> domainList = personList.stream().map(person -> {
            String[] mailArr = person.getEmail().split("@");
            return mailArr[mailArr.length-1];
        }).distinct().collect(Collectors.toList());
        Map<String, StatDto> responseMap = new HashMap<>();
        responseMap.put("emails", StatDto.builder().data(emailList).count(emailList.size()).build());
        responseMap.put("domains", StatDto.builder().data(domainList).count(domainList.size()).build());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }


}
