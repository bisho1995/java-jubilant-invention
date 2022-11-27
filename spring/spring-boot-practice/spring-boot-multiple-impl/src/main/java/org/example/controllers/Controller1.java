package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.components.entity1.Entity1;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "path1")
@AllArgsConstructor
public class Controller1 {

    List<Entity1> entities;

    @RequestMapping(path = "/path2/subPath1", method = RequestMethod.GET)
    public String index(){
        entities.forEach(entity->log.info("entity -> {}", entity.getValue()));
        return entities.get(0).getValue();
    }
}
