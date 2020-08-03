package com.example.teamvoy.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CommonUtil {

    public static <T> void setIfNotNull(Supplier<T> getter, Consumer<T> setter) {
        setIf(Objects::nonNull, getter, setter);
    }

    private static <T> void setIf(Predicate<T> condition, Supplier<T> getter, Consumer<T> setter) {
        if (condition.test(getter.get())) {
            setter.accept(getter.get());
        }
    }

}
