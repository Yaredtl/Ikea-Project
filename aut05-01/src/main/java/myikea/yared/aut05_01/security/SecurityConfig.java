package myikea.yared.aut05_01.security;

import jakarta.annotation.PostConstruct;
import myikea.yared.aut05_01.models.Role;
import myikea.yared.aut05_01.models.User;
import myikea.yared.aut05_01.repositories.RoleRepository;
import myikea.yared.aut05_01.repositories.UserRepository;
import myikea.yared.aut05_01.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserServiceImp userService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                //LOS QUE NO ESTAN LOGUEADOS PODRAN ESTAR EN ESTAS PAGINAS
                .requestMatchers("/", "/register", "/login").permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
        );
        http.logout(form -> form
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );


        http.userDetailsService(userService);
        return http.build();
    }

    @PostConstruct
    public void seedingUserRole(){

        try{

            //verificamos si tenemos los roles creados de por si para si no, crearlos
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            if (adminRole == null){
                adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            Role managerRole = roleRepository.findByName("ROLE_MANAGER");
            if(managerRole == null){
                managerRole = new Role();
                managerRole.setName("ROLE_MANAGER");
                roleRepository.save(managerRole);
            }

            Role userRole = roleRepository.findByName("ROLE_USER");
            if(userRole == null){
                userRole = new Role();
                userRole.setName("ROLE_USER");
                roleRepository.save(userRole);
            }

            //Verificamos igual que los roles de si existe.
            User adminUser = userRepository.findByEmail("admin1@myikea.com");
            if (adminUser == null){
                adminUser = new User();
                adminUser.setName("admin1@myikea.com");
                adminUser.setEmail("admin1@myikea.com");
                adminUser.setPassword(passwordEncoder().encode("Qwer123!"));
                //incluimos como lista de roles el rol de admin
                adminUser.setRoles(Collections.singletonList(adminRole));
                userRepository.save(adminUser);
            }

            User managerUser = userRepository.findByEmail("manager@myikea.com");
            if (managerUser == null){
                managerUser = new User();
                managerUser.setName("manager@myikea.com");
                managerUser.setEmail("manager@myikea.com");
                managerUser.setPassword(passwordEncoder().encode("Qwer123!"));
                managerUser.setRoles(Collections.singletonList(managerRole));
                userRepository.save(managerUser);
            }

            User standardUser = userRepository.findByEmail("user@myikea.com");
            if (standardUser == null) {
                standardUser = new User();
                standardUser.setName("user@myikea.com");
                standardUser.setEmail("user@myikea.com");
                standardUser.setPassword(passwordEncoder().encode("Qwer123!"));
                standardUser.setRoles(Collections.singletonList(userRole));
                userRepository.save(standardUser);
            }
            User adminUser2 = userRepository.findByEmail("admin2@myikea.com");
            if (adminUser2 == null){
                adminUser2 = new User();
                adminUser2.setName("admin2@myikea.com");
                adminUser2.setEmail("admin2@myikea.com");
                //incluimos como lista de roles el rol de admin para añadir ambos roles
                List<Role> roles = new ArrayList<>();
                roles.add(adminRole);
                roles.add(managerRole);
                adminUser2.setRoles(roles);
                adminUser2.setPassword(passwordEncoder().encode("Qwer123!"));
                userRepository.save(adminUser2);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
