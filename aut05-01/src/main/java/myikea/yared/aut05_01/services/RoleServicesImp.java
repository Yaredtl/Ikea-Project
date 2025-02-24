package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Role;
import myikea.yared.aut05_01.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleServicesImp implements RoleServices{

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listRoles(){
        return roleRepository.findAll();
    }

    public Optional<Role> getRole(int id){
        return roleRepository.findById(id);
    }

    public void crearRole(Role role) {
        roleRepository.save(role);
    }
}
