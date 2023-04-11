package org.learning.springlamiapizzeriacrud.service;

import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.models.Category;
import org.learning.springlamiapizzeriacrud.models.Pizza;
import org.learning.springlamiapizzeriacrud.repositories.CategoryRepository;
import org.learning.springlamiapizzeriacrud.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Pizza createPizza(Pizza formPizza) {
        Pizza pizzaToPersist = new Pizza();
        pizzaToPersist.setName(formPizza.getName());
        pizzaToPersist.setDescription(formPizza.getDescription());
        pizzaToPersist.setPrice(formPizza.getPrice());
        pizzaToPersist.setCategories(formPizza.getCategories());

        Set<Category> formCategories = getPizzaCategories(formPizza);
        pizzaToPersist.setCategories(formCategories);
        return pizzaRepository.save(pizzaToPersist);
    }

    public Pizza updatePizza(Pizza formPizza, Integer id) throws PizzaNotFoundException {
        Pizza pizzaToUpdate = getById(id);
        pizzaToUpdate.setName(formPizza.getName());
        pizzaToUpdate.setDescription(formPizza.getDescription());
        pizzaToUpdate.setPrice(formPizza.getPrice());
        pizzaToUpdate.setCategories(formPizza.getCategories());

        Set<Category> formCategories = getPizzaCategories(formPizza);
        pizzaToUpdate.setCategories(formCategories);
        return pizzaRepository.save(pizzaToUpdate);
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll(Sort.by("name"));
    }

    public List<Pizza> getFilteredPizzas(String keyword) {
        return pizzaRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Pizza getById(Integer id) throws PizzaNotFoundException {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PizzaNotFoundException(Integer.toString(id));
        }
    }

    public boolean deleteById(Integer id) {
        pizzaRepository.findById(id).orElseThrow(() -> new PizzaNotFoundException(Integer.toString(id)));
        try {
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Set<Category> getPizzaCategories(Pizza formPizza) {
        Set<Category> formCategories = new HashSet<>();
        for (Category c : formPizza.getCategories()) {
            formCategories.add(categoryRepository.findById(c.getId()).orElseThrow());
        }
        return formCategories;
    }
}
