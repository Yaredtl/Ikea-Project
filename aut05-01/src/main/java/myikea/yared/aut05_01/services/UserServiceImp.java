package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Role;
import myikea.yared.aut05_01.repositories.RoleRepository;
import myikea.yared.aut05_01.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public myikea.yared.aut05_01.models.User createUser(myikea.yared.aut05_01.models.User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roleDefault = new ArrayList<>();
        roleDefault.add(role);
        user.setRoles(roleDefault);
        return userRepository.save(user);
    }
    public myikea.yared.aut05_01.models.User getUser(String email){
        return userRepository.findByEmail(email);
    }
    @Override
    public UserDetails loadUserByUsername(String email) {
        myikea.yared.aut05_01.models.User user = userRepository.findByEmail(email);

        //Recogemos los "permisos" que podrá tener el user en base a sus roles
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        //Devolvemos el user con to-do lo necesario revisado
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

}

