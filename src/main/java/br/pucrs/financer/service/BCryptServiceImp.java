package br.pucrs.financer.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptServiceImp implements BCryptService{

    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
