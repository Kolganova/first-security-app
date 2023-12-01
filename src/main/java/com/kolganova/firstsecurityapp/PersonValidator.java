package com.kolganova.firstsecurityapp;

import com.kolganova.firstsecurityapp.models.Person;
import com.kolganova.firstsecurityapp.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> optPerson = peopleService.loadUserByUsername(person.getUsername());

        if (optPerson.isPresent()) {
            errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");
        }
    }
}
