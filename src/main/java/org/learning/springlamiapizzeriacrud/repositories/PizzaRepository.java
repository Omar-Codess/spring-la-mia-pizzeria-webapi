package org.learning.springlamiapizzeriacrud.repositories;

import org.learning.springlamiapizzeriacrud.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository <Pizza, Integer> {

}
