package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;
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
    private  CustomMapper customMapper;

    @Autowired
    private void setCustomMapper(CustomMapper customMapper) {
        this.customMapper = customMapper;
    }

    public List< InstitutionDTO> getAll() {
        List<Institution> institutions = institutionRepository.findAll();
        return institutions.stream().map(customMapper::map).collect(Collectors.toList());
    }
public Institution getById(Long id){
        return institutionRepository.getOne(id);
}


}
