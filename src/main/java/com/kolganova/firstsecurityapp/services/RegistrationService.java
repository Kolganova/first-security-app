package com.kolganova.firstsecurityapp.services;

import com.kolganova.firstsecurityapp.models.Person;
import com.kolganova.firstsecurityapp.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    @Transactional
    public void register(Person person) {
        peopleRepository.save(person);
    }

}
