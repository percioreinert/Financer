package br.pucrs.financer.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Year;
import java.util.*;

@Entity
public class User implements UserDetails {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @OneToOne
    private Map<Year, ArrayList<Item>> items = new HashMap<>();

    // == public methods ==
    public Map<Year, ArrayList<Item>> getItems() {
        return Collections.unmodifiableMap(this.items);
    }

    public void setItems(Map<Year, ArrayList<Item>> items) {
        this.items = items;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setItemInMap(Item item) {
        ArrayList<Item> list = items.get(Year.now());
        list.add(item);
        items.put(Year.now(), list);
    }

    public void removeItemInMap(Integer id) {
        ArrayList<Item> list = items.get(Year.now());
        list.remove((int) id);
        items.put(Year.now(), list);
    }
}
