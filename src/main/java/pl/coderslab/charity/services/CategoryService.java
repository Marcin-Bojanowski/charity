package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.category.CategoryDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.utilities.CustomMapper;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CustomMapper customMapper;

    public List<CategoryDTO> getAll() {
        System.out.println(categoryRepository.getAll());
        return customMapper.categoriesDTOMapper(categoryRepository.getAll());
    }

    public Category getById(Long id){
        return categoryRepository.getById(id);
    }
}
