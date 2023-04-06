package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.models.Category;
import org.learning.springlamiapizzeriacrud.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){

    }
}
