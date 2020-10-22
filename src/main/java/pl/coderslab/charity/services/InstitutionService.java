package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
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
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final CustomMapper customMapper;

    public List< InstitutionDTO> getAll() {
        List<Institution> institutions = institutionRepository.getAll();
        return customMapper.institutionsDTOMapper(institutions);
    }
public Institution getById(Long id){
        return institutionRepository.getById(id);
}


}
