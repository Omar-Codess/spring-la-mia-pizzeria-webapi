package org.learning.springlamiapizzeriacrud.repositories;

import org.learning.springlamiapizzeriacrud.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository <Pizza, Integer> {
    public List<Pizza> findByNameContainingIgnoreCase(String name);
}
