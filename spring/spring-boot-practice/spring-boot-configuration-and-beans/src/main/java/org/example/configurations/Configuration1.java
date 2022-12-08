package org.example.configurations;

import org.example.beans.Bean1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration1 {
    @Bean
    public Bean1 myBean1(){
        return new Bean1();
    }
}
