package myikea.yared.aut05_01.repositories;

import myikea.yared.aut05_01.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
