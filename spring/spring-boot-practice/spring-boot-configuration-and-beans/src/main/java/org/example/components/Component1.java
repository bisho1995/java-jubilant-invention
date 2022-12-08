package org.example.components;

import lombok.extern.slf4j.Slf4j;
import org.example.beans.Bean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Component1 {
    ApplicationContext context;

    @Autowired
    Component1(ApplicationContext context){
        this.context=context;

        Bean1 bean1_1 = context.getBean(Bean1.class);
        Bean1 bean1_2 = context.getBean(Bean1.class);

        log.info("bean1_1 hashcode {}", bean1_1.hashCode());
        log.info("bean1_1 hashcode {}", bean1_2.hashCode());
    }
}
