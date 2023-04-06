package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.models.Category;
import org.learning.springlamiapizzeriacrud.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll(Sort.by("name"));
    }

    public Category create (Category formCategory){
        Category categoryToCreate = new Category();
        categoryToCreate.setName(formCategory.getName());
        categoryToCreate.setDescription(formCategory.getDescription());
        return categoryRepository.save(categoryToCreate);
    }
}
