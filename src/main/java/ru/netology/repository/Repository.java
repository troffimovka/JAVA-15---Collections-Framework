package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class Repository {
    Collection<Issue> items = new ArrayList<>();

    //Базовые методы для обеспечения работы обязательных функций
    // Возврат всех Issues в виде коллекции
    public Collection<Issue> findAll() {
        return items;
    }

    // Добавление передаваемой коллекции объектов в коллекцию Issues
    public boolean saveAll(Collection<Issue> items_to_add) {
        return items.addAll(items_to_add);
    }

    //Поиск Issue по ID
    public Issue findById(int id) throws NotFoundException {
        for (Issue item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new NotFoundException("Не найдено ID " + id);
    }

    //Удаление Issue по ID
    public boolean removeByID(int id) {
        return items.remove(findById(id));
    }

    // 1. Обязательная функция: Добавление Issue
    public void save(Issue item) {
        items.add(item);
    }
}



