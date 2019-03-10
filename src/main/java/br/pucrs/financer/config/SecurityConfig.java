package br.pucrs.financer.config;

import br.pucrs.financer.service.BCryptService;
import br.pucrs.financer.service.BCryptServiceImp;
import br.pucrs.financer.service.UserService;
import br.pucrs.financer.service.UserServiceImp;
import br.pucrs.financer.util.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // == fields ==
    private final UserService userService;
    private final BCryptService bCryptService;

    // == constructors ==
    @Autowired
    public SecurityConfig(UserServiceImp userService, BCryptServiceImp bCryptService) {
        this.userService = userService;
        this.bCryptService = bCryptService;
    }

    // == override methods ==
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/img/**").permitAll()
                .antMatchers(Mappings.REGISTER).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(Mappings.LOGIN)
                .defaultSuccessUrl(Mappings.ITEMS + Mappings.ITEMS_LIST).permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .logoutSuccessUrl(Mappings.LOGIN);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptService.encoder());
        return authProvider;
    }
}
