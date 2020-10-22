package pl.coderslab.charity.utilities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.dtos.category.CategoryDTO;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Institution;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class CustomMapper  {
private final ModelMapper modelMapper;


    public List <InstitutionDTO> institutionsDTOMapper(List<Institution> institutions){
        return institutions.stream().map (institution -> modelMapper.map(institution,InstitutionDTO.class)).collect(Collectors.toList());
    }

    public List<CategoryDTO> categoriesDTOMapper(List <Category> categories){
        return categories.stream().map (category -> modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
    }


}
