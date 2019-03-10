package br.pucrs.financer.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface BCryptService {

    BCryptPasswordEncoder encoder();
}
