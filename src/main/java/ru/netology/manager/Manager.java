package ru.netology.manager;

import lombok.Data;
import ru.netology.comparator.ComparatorByCreationDate;
import ru.netology.comparator.ComparatorByCreationReverse;
import ru.netology.comparator.ComparatorByUpdate;
import ru.netology.comparator.ComparatorByUpdateReverse;
import ru.netology.domain.Issue;
import ru.netology.exceptions.NotFoundException;
import ru.netology.repository.Repository;

import java.util.*;
import java.util.function.Predicate;

@Data
public class Manager {
    private Repository repository = new Repository();

    //Базовые методы для обеспечения работы обязательных функций
    //добавить Issue
    public void add(Issue item) {
        repository.save(item);
    }

    // Возврат всех Issues в виде коллекции
    public Collection<Issue> getAll() {
        return repository.findAll();
    }

    //Поиск Issue по ID
    public Issue getById(int id) {
        return repository.findById(id);
    }

    // Добавление передаваемой коллекции объектов в коллекцию Issues
    public void addAll(Collection<Issue> items) {
        repository.saveAll(items);
    }

    // 2. Обязательная функция: Отображение списка открытых Issue
    public Collection<Issue> showOpenIssues() {
        Collection<Issue> opened = new ArrayList<>();
        for (Issue item : getAll()) {
           if (!item.isClosedIssue()) {
                opened.add(item);
            }
        }
        return opened;
    }

    // 2. Обязательная функция: Отображение списка закрытых Issue
    public Collection<Issue> showClosedIssues() {
        Collection<Issue> closed = new ArrayList<>();
        for (Issue item : getAll()) {
            if (item.isClosedIssue()) {
                closed.add(item);
            }
        }
        return closed;
    }

    // 3. Обязательная функция: Фильтрация по имени Автора
    public Collection<Issue> filterByAuthor(Predicate<Set<String>> author) {
        Collection<Issue> filteredByAuthor = new ArrayList<>();
        for (Issue item : getAll()) {
            if (author.test(item.getAuthor())) {
                filteredByAuthor.add(item);
            }
        }
        return filteredByAuthor;
    }

    // 3. Обязательная функция: Фильтрация по Assignee
    public Collection<Issue> filterByAssignee(Predicate<Set<String>> assignee) {
        Collection<Issue> filteredByAssignee = new ArrayList<>();
        for (Issue item : getAll()) {
            if (assignee.test(item.getAssignee())) {
                filteredByAssignee.add(item);
            }
        }
        return filteredByAssignee;
    }

    // 3. Обязательная функция: Фильтрация по тегу
    public Collection<Issue> filterByLabel(Predicate<Set<String>> label) {
        Collection<Issue> filteredByLabel = new ArrayList<>();
        for (Issue item : getAll()) {
            if (label.test(item.getLabel())) {
                filteredByLabel.add(item);
            }
        }
        return filteredByLabel;
    }

    // 4. Обязательная функция: Сортировка по ID
    public Collection<Issue> sortById() {
        List<Issue> result = (List) getAll();
        Collections.sort(result);
        return result;
    }

    // 4. Обязательная функция: Сортировка по Дате создания (сначала старые)
    public Collection<Issue> sortByCreationDate(ComparatorByCreationDate dateOfCreation) {
        List<Issue> result = (List) getAll();
        Collections.sort(result, dateOfCreation);
        return result;
    }

    // 4. Обязательная функция: Сортировка по Дате создания (сначала новые)
    public Collection<Issue> sortByCreationDateReverse(ComparatorByCreationReverse creationDateReverse) {
        List<Issue> result = (List) getAll();
        Collections.sort(result, creationDateReverse);
        return result;
    }

    // 4. Обязательная функция: Сортировка по Дате Обновления Issue (сначала старые)
    public Collection<Issue> sortByUpdate(ComparatorByUpdate dateOfUpdate) {
        List<Issue> result = (List) getAll();
        Collections.sort(result, dateOfUpdate);
        return result;
    }

    // 4. Обязательная функция: Сортировка по Дате Обновления Issue (сначала новые)
    public Collection<Issue> sortByUpdateReverse(ComparatorByUpdateReverse updateDateReverse) {
        List<Issue> result = (List) getAll();
        Collections.sort(result, updateDateReverse);
        return result;
    }

    // 5. Обязательная функция: Закрытие Issue по id (выкидывает NotFoundException если такого ID нет)
    public void closeByID(int id) {
        Issue newIssue = getById(id);
        if (newIssue.isClosedIssue() == false) {
            newIssue.setClosedIssue(true);
            repository.removeByID(id);
            repository.save(newIssue);
        } else {
            System.out.println("This issue is already closed");
        }
    }

    // 5. Обязательная функция: Открытие Issue по id (выкидывает NotFoundException если такого ID нет)
    public void openByID(int id) {
        Issue newIssue = getById(id);
        if (newIssue.isClosedIssue() == true) {
            newIssue.setClosedIssue(false);
            repository.removeByID(id);
            add(newIssue);
        } else {
            System.out.println("This issue is already opened");
        }
    }
}
