package myikea.yared.aut05_01.repositories;

import myikea.yared.aut05_01.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String roleUser);
}
