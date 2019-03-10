package br.pucrs.financer.service;

import br.pucrs.financer.model.Item;
import br.pucrs.financer.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.Year;
import java.util.ArrayList;
import java.util.Map;

public interface UserService extends UserDetailsService {

    void save(User user);

    void setItem(User user, Item item);

    void removeItem(User user, Integer id);

    Map<Year, ArrayList<Item>> getItems(User user);
}
