package org.example.components.entity1;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class Entity1Impl1 implements Entity1 {
    @Override
    public String getValue() {
        return "Entity1Impl1";
    }
}
