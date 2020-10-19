package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.repositories.InstitutionRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
}
