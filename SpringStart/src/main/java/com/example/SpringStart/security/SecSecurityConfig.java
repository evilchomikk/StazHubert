package com.example.SpringStart.security;

import com.example.SpringStart.commons.dto.customer.CustomerDTO;
import com.example.SpringStart.tables.customer.repository.CustomerRepository;
import com.example.SpringStart.tables.customer.model.Customer;
import com.example.SpringStart.tables.customer.service.CustomerService;
import com.example.SpringStart.tables.customer.userDetails.CustomerUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecSecurityConfig {


    private final CustomerService customerService;

    @Bean
    public UserDetailsService userDetailsService() {

        List<CustomerDTO> customers = customerService.getCustomers();
        List<UserDetails> userDetailsList = new ArrayList<>();

        for (CustomerDTO customer : customers) {
            customer.getLoginData().setPassword(new BCryptPasswordEncoder().encode(customer.getLoginData().getPassword()));
            userDetailsList.add(new CustomerUserDetails(customer));
        }

        return new InMemoryUserDetailsManager(userDetailsList);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/customer/get").hasRole("customer")
                        .requestMatchers("/product/add").hasRole("customer")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin
                        //.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/home", true)
                        .failureForwardUrl("/login")
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}