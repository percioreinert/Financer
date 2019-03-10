package br.pucrs.financer.controller;

import br.pucrs.financer.util.Mappings;
import br.pucrs.financer.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // == mappings ==
    @GetMapping(Mappings.LOGIN)
    public String loginForm() {
        return ViewNames.LOGIN;
    }

    // @PostMapping(Mappings.LOGIN) -> Is automatically used and found by Spring, doing the User validation by itself.
}
