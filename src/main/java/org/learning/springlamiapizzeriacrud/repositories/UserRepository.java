package org.learning.springlamiapizzeriacrud.repositories;

import java.util.Optional;
import org.learning.springlamiapizzeriacrud.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
