package org.example.components.entity1;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Entity1Impl2 implements Entity1 {
    @Override
    public String getValue() {
        return "Entity1Impl2";
    }
}
