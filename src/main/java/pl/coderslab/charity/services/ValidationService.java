package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.repositories.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidationService {
    private final UserRepository userRepository;


    public boolean isUniqueEmail(String email) {
        return !userRepository.existsByEmail(email);
    }

}
