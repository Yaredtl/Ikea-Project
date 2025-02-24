package myikea.yared.aut05_01.controllers;

import jakarta.validation.Valid;
import myikea.yared.aut05_01.models.User;
import myikea.yared.aut05_01.repositories.UserRepository;
import myikea.yared.aut05_01.services.RoleServices;
import myikea.yared.aut05_01.services.UserServiceImp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController {

    private UserServiceImp userService;
    private RoleServices roleService;
    private UserRepository userRepository;

    public AuthController(UserServiceImp userService, RoleServices roleService, UserRepository userRepository){
        this.roleService = roleService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(Model model){
        return "Auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "Auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "Auth/register";
        }
        else{
            if(userService.getUser(user.getEmail()) != null){
                System.out.println("Este usuario ya está creado");
                return "Auth/register";
            }
            userService.createUser(user);
            return "redirect:/login";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String listUsers(Model model, @AuthenticationPrincipal UserDetails loggedUser){
        List<User> users = userRepository.findAll();
        users = users.stream()
                        .filter(user -> !user.getEmail().equals(loggedUser.getUsername()))
                        .collect(Collectors.toList());
        model.addAttribute("Usuarios", users);
        return "Auth/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
