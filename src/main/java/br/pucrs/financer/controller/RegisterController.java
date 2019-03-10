package br.pucrs.financer.controller;

import br.pucrs.financer.model.User;
import br.pucrs.financer.service.UserService;
import br.pucrs.financer.service.UserServiceImp;
import br.pucrs.financer.util.AttributeNames;
import br.pucrs.financer.util.Mappings;
import br.pucrs.financer.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    // == fields ==
    private final UserService userService;

    // == constructors ==
    @Autowired
    public RegisterController(UserServiceImp userService) {
        this.userService = userService;
    }

    // == mappings ==
    @GetMapping(Mappings.REGISTER)
    public String registerForm(Model model) {
        model.addAttribute(AttributeNames.USER, new User());
        return ViewNames.REGISTER;
    }

    @PostMapping(Mappings.REGISTER)
    public String registerSubmit(@ModelAttribute User user) {
        userService.save(user);

        return Mappings.REDIRECT_LOGIN;
    }
}
