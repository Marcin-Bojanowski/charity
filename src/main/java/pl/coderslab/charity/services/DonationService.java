package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.repositories.DonationRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DonationService {
    private final DonationRepository donationRepository;
}
