package br.pucrs.financer.controller;

import br.pucrs.financer.model.Item;
import br.pucrs.financer.model.User;
import br.pucrs.financer.service.UserService;
import br.pucrs.financer.util.AttributeNames;
import br.pucrs.financer.util.Mappings;
import br.pucrs.financer.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Mappings.ITEMS)
public class ItemsController {

    // == fields ==
    private final UserService userService;

    // == constructors ==
    @Autowired
    public ItemsController(UserService userService) {
        this.userService = userService;
    }

    // == mappings ==
    @GetMapping(Mappings.ITEMS_LIST)
    public String itemsList() {
        return ViewNames.ITEMS_LIST;
    }

    @GetMapping(Mappings.ADD_ITEM)
    public String addItem() {
        return ViewNames.ADD_ITEM;
    }

    @PostMapping(Mappings.ADD_ITEM)
    public String addItem(@AuthenticationPrincipal User user, Item item) {
        userService.setItem(user, item);
        return ViewNames.ITEMS_LIST;
    }

    @PostMapping(Mappings.REMOVE_ITEM)
    public String removeItem(@AuthenticationPrincipal User user, Integer id, Model model) {
        userService.removeItem(user, id);
        model.addAttribute(AttributeNames.ITEMS_LIST, userService.getItems(user));
        return ViewNames.ITEMS_LIST;
    }
}
