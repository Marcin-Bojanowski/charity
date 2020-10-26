package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.category.CategoryDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.utilities.CustomMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class CategoryService {
    private final CategoryRepository categoryRepository;
    private CustomMapper customMapper;

    @Autowired
    private void setCustomMapper(CustomMapper customMapper) {
        this.customMapper = customMapper;
    }



    public List<CategoryDTO> getAll() {
       List <Category> categories=categoryRepository.findAll();
        log.info("{}",categories);
        return categories.stream().map(customMapper::map).collect(Collectors.toList());
    }

    public Category getById(Long id){
        return categoryRepository.getOne(id);
    }
}
