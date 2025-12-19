package com.swaggerpetstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StoreEnums {
    @Getter
    @AllArgsConstructor
    public enum Status {
        PLACED("placed"),
        CANCELLED("cancelled"),
        SHIPPED("shipped"),
        DELIVERED("delivered");
        private final String value;
    }

    @Getter
    @AllArgsConstructor
    public enum Complete {
        TRUE(true),
        FALSE(false);
        private final boolean flag;
    }
}
