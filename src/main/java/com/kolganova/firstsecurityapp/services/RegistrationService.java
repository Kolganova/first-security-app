package com.kolganova.firstsecurityapp.services;

import com.kolganova.firstsecurityapp.models.Person;
import com.kolganova.firstsecurityapp.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Person person) {
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        person.setRole("ROLE_USER");

        peopleRepository.save(person);
    }

}
