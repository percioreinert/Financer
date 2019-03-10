package br.pucrs.financer.service;

import br.pucrs.financer.model.Item;
import br.pucrs.financer.model.User;
import br.pucrs.financer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    // == fields ==
    private final UserRepository userRepository;
    private final BCryptService bCryptService;

    // == constructors ==
    @Autowired
    public UserServiceImp(UserRepository userRepository, BCryptServiceImp bCryptService) {
        this.userRepository = userRepository;
        this.bCryptService = bCryptService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        user.setPassword(bCryptService.encoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void setItem(User user, Item item) {
        user.setItemInMap(item);
    }

    @Override
    public void removeItem(User user, Integer id) {
        user.removeItemInMap(id);
    }

    @Override
    public Map<Year, ArrayList<Item>> getItems(User user) {
        return user.getItems();
    }
}
