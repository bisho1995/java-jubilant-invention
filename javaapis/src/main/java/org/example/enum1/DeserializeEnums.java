package org.example.enum1;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

public class DeserializeEnums {
    public static void main(String[] args) {
        Enum1 invoiceType = Enum1.fromValue("1");
        System.out.println(invoiceType);

        invoiceType = Enum1.fromValue("VAL1");
        System.out.println(invoiceType.getValue());
        Enum1 enum1 = Enum1.fromValue("1");
        System.out.println(enum1);
    }

    @AllArgsConstructor
    @Getter
    public enum Enum1 {
        VAL1("1");

        private final String value;

        public static Enum1 fromValue(String value) {
            return Arrays.stream(Enum1.values())
                    .filter(v -> v.getValue().equals(value) || v.name().equals(value))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid value: " + value));
        }
    }
}
