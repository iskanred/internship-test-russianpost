package dev.iskandev.internshiptestrussianpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class InternshipTestRussianpostApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipTestRussianpostApplication.class, args);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .requiresChannel(channel ->
                    channel.anyRequest().requiresSecure())
                .authorizeRequests(authorize ->
                        authorize.anyRequest().permitAll())
                .build();
    }
}
