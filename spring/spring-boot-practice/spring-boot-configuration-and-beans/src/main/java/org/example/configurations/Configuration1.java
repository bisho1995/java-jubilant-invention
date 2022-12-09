package org.example.configurations;

import org.example.beans.Bean1;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Tags the class as a source of bean definitions for the application context
public class Configuration1 {
    @Bean // The bean is instantiated in eager instantiation way & kept ready
    public Bean1 myBean1(){
        return new Bean1();
    }
}
