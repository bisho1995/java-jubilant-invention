package org.example.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class Bean1 {
    @Autowired
    public Bean1(){
        log.info("loading bean1 constructor");
    }
    public void execute(){
        log.info("I am bean1");
    }
}
