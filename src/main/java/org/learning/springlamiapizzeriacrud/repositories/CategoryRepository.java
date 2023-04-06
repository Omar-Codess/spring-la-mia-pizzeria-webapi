package org.learning.springlamiapizzeriacrud.repositories;

import org.learning.springlamiapizzeriacrud.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
