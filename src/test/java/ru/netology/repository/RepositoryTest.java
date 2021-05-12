package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.exceptions.NotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Repository repository = new Repository();
    private boolean closed = true;
    private boolean opened = false;
    private Issue first = new Issue(1, "Challenge1", Issue.setAuthor("Pushkin"), Issue.setLabel("Red"), Issue.setAssignee("Padavan"), opened, Issue.setDateOfCreation(2017, 0, 25), Issue.setDateOfUpdate(2019, 1, 25));
    private Issue second = new Issue(2, "Challenge2", Issue.setAuthor("Gogol"), Issue.setLabel("Green"), Issue.setAssignee("Jeday"), opened, Issue.setDateOfCreation(2018, 0, 25), Issue.setDateOfUpdate(2019, 1, 26));
    private Issue third = new Issue(3, "Challenge3", Issue.setAuthor("Pushkin"), Issue.setLabel("Blue"), Issue.setAssignee("Padavan"), closed, Issue.setDateOfCreation(2019, 0, 25), Issue.setDateOfUpdate(2019, 1, 26));
    private Issue forth = new Issue(4, "Challenge4", Issue.setAuthor("Lermontov"), Issue.setLabel("Red"), Issue.setAssignee("Jeday"), closed, Issue.setDateOfCreation(2020, 0, 25), Issue.setDateOfUpdate(2020, 1, 26));

    @BeforeEach
    void setUp() {
        repository.saveAll(List.of(first, second, third, forth));
    }

    // Проверка обязательной функции: 1.Добавление Issue
    @Test
    void shouldAddIssue() {
        Issue newIssue = new Issue(5, "Challenge5", Issue.setAuthor("Po"), Issue.setLabel("Blue"), Issue.setAssignee("Jeday"), closed, Issue.setDateOfCreation(2020, 5, 20), Issue.setDateOfUpdate(2020, 9, 26));
        repository.save(newIssue);
        Issue expected = repository.findById(5);
        Issue actual = newIssue;
        assertEquals(expected, actual);
    }

    //Тесты для проверки базовых функций
    @Test
    void shouldFindAllIssues() {
        Collection<Issue> expected = List.of(first, second, third, forth);
        Collection<Issue> actual = repository.findAll();
        assertIterableEquals(expected, actual);
        for (Issue item : actual)
            System.out.println("ID: " + item.getId() + " Наименование: " + item.getName() + " Автор: " + item.getAuthor() + " Тег: " + item.getLabel() + " Назначено: " + item.getAssignee() + " Статус: " + (item.isClosedIssue() == closed ? "Closed" : "Open"));
    }

    @Test
    void shouldFindExistingId() {
        int idToFind = 4;
        Issue expected = forth;
        Issue actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    void shouldGenerateExceptionWhenTryToFindMissingID() {
        int idToRemove = 5;
        Exception e = assertThrows(NotFoundException.class, () -> repository.findById(idToRemove));
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}