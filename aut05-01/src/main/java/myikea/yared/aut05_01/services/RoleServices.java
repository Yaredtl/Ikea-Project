package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleServices {
    List<Role> listRoles();

    Optional<Role> getRole(int id);

    void crearRole(Role role);
}
