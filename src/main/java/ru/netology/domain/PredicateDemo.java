package ru.netology.domain;

import ru.netology.manager.Manager;

public interface PredicateDemo {
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);

    }
}

