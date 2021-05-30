package me.burakaykan.task.model;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class Person {

    private UUID id;
    private boolean active;
    private String balance;
    private String picture;
    private int age;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String phone;
    private String address;
    private String greeting;
}