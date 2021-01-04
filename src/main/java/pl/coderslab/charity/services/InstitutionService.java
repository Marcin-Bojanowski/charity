package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;
import pl.coderslab.charity.dtos.institution.NewInstitutionDTO;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.utilities.CustomMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private CustomMapper customMapper;
    private DonationService donationService;

    @Autowired
    private void setCustomMapper(CustomMapper customMapper) {
        this.customMapper = customMapper;
    }

    @Autowired
    private void setDonationService(DonationService donationService){
        this.donationService=donationService;
    }

    public List<InstitutionDTO> getAll() {
        List<Institution> institutions = institutionRepository.findAll();
        return institutions.stream().map(customMapper::map).collect(Collectors.toList());
    }

    public Institution getById(Long id) {
        return institutionRepository.getOne(id);
    }

    public InstitutionDTO getDtoById(Long id) {
        return customMapper.map(institutionRepository.getOne(id));
    }


    public void save(NewInstitutionDTO newInstitution) {
        Institution institution = new Institution();
        institution = customMapper.map(newInstitution);
        institutionRepository.save(institution);
    }

    public void update(InstitutionDTO institutionDTO) {
        Institution institution = institutionRepository.getOne(institutionDTO.getId());
        institution.setName(institutionDTO.getName());
        institution.setDescription(institutionDTO.getDescription());
        institutionRepository.save(institution);
    }

    public void delete(Long id) {
        Institution institution = institutionRepository.getOne(id);
        List<Donation> donations = donationService.getAllByInstitution(institution);
        log.info(donations.toString());
        institutionRepository.delete(institution);
        donations.forEach(donation -> donation.setInstitution(null));
        log.info(donations.toString());
        donations.forEach(donationService::update);
    }
}
