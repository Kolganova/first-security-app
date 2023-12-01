package com.kolganova.firstsecurityapp.services;

import com.kolganova.firstsecurityapp.models.Person;
import com.kolganova.firstsecurityapp.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public Optional<Person> loadUserByUsername(String username) throws UsernameNotFoundException {
        return peopleRepository.findByUsername(username);
    }

}
