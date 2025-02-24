package myikea.yared.aut05_01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public  String Main (){
        return "index";
    }
}
