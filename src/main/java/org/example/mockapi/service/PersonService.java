package org.example.mockapi.service;

import org.example.mockapi.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final List<Person> people = new ArrayList<>(List.of(
            new Person(1L, "Pavol", "Martinák", 30,
                    "pavol.martinak@example.com", "+421123456789", "Bratislava", true),
            new Person(2L, "Ivana", "Chudá", 25,
                    "ivana.chuda@example.com", "+421987654321", "Košice", false),
            new Person(3L, "Peter", "Novák", 40,
                    "peter.novak@example.com", "+421901112233", "Trnava", true),
            new Person(4L, "Lucia", "Horváthová", 28,
                    "lucia.horvathova@example.com", "+421902998877", "Žilina", true),
            new Person(5L, "Marek", "Kováč", 35,
                    "marek.kovac@example.com", "+421903556677", "Prešov", false)
    ));

    public List<Person> getPeople(Boolean active) {
        if (active == null) {
            return people;
        }
        return people.stream()
                .filter(p -> p.isActive() == active)
                .collect(Collectors.toList());
    }

    public Optional<Person> getPersonById(Long id) {
        return people.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Optional<Person> findById(Long id) {
        return people.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Person addPerson(Person newPerson) {
        long newId = people.stream().mapToLong(Person::getId).max().orElse(0L) + 1;
        newPerson.setId(newId);
        people.add(newPerson);
        return newPerson;
    }

    public boolean deletePerson(Long id) {
        return people.removeIf(person -> person.getId().equals(id));
    }
}
