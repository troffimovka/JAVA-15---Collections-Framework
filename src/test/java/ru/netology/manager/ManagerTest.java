package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.ComparatorByCreationDate;
import ru.netology.comparator.ComparatorByCreationReverse;
import ru.netology.comparator.ComparatorByUpdate;
import ru.netology.comparator.ComparatorByUpdateReverse;
import ru.netology.domain.Issue;
import ru.netology.exceptions.NotFoundException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Manager manager = new Manager();
    ComparatorByCreationDate olderCreationDate = new ComparatorByCreationDate();
    ComparatorByCreationReverse recentCreationDate = new ComparatorByCreationReverse();
    ComparatorByUpdate olderUpdate = new ComparatorByUpdate();
    ComparatorByUpdateReverse recentUpdate = new ComparatorByUpdateReverse();
    private boolean closed = true;
    private boolean opened = false;

    private Issue first = new Issue(1, "Challenge1", Issue.setAuthor("Pushkin"), Issue.setLabel("Red"), Issue.setAssignee("Padavan"), opened, Issue.setDateOfCreation(2017, 0, 25), Issue.setDateOfUpdate(2019, 1, 25));
    private Issue second = new Issue(2, "Challenge2", Issue.setAuthor("Gogol"), Issue.setLabel("Green"), Issue.setAssignee("Jeday"), opened, Issue.setDateOfCreation(2018, 0, 25), Issue.setDateOfUpdate(2018, 1, 26));
    private Issue third = new Issue(3, "Challenge3", Issue.setAuthor("Pushkin"), Issue.setLabel("Blue"), Issue.setAssignee("Padavan"), closed, Issue.setDateOfCreation(2019, 0, 25), Issue.setDateOfUpdate(2019, 1, 25));
    private Issue forth = new Issue(4, "Challenge4", Issue.setAuthor("Lermontov"), Issue.setLabel("Red"), Issue.setAssignee("Jeday"), closed, Issue.setDateOfCreation(2020, 0, 25), Issue.setDateOfUpdate(2020, 1, 26));

    @BeforeEach
    void setUp() {
        manager.addAll(List.of(first, second, third, forth));
    }

    //Вспомогательный метод для форматирования даты в читаемый вид для вывода в консоль
    private String dateFormatting(Calendar date, String pattern) {
        DateFormat newDate = new SimpleDateFormat(pattern);
        return newDate.format(date.getTime());
    }

    // 2. Проверка обязательной функции: Отображение списка открытых Issue
    @Test
    void shouldShowAllOpenIssues() {
        Collection<Issue> expected = List.of(first, second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertIterableEquals(expected, actual);
        for (Issue item : actual)
            System.out.println("ID: " + item.getId() + " Status: " + (item.isClosedIssue() ? "Closed" : "Open"));
    }

    // 2. Проверка обязательной функции: Отображение списка закрытых Issue
    @Test
    void shouldShowAllClosedIssues() {
        Collection<Issue> expected = List.of(third, forth);
        Collection<Issue> actual = manager.showClosedIssues();
        assertIterableEquals(expected, actual);
        for (Issue item : actual)
            System.out.println("ID: " + item.getId() + " Status: " + (item.isClosedIssue() ? "Closed" : "Open"));
    }

    //3. Проверка обязательной функции: Фильтрация по имени Автора
    @Test
    void shouldFilterByAuthor() {
        Set<String> author = new HashSet(Arrays.asList("Pushkin"));
        Collection<Issue> actual = manager.filterByAuthor(a -> a.equals(author));
        Collection<Issue> expected = List.of(first, third);
        for (Issue item : actual)
            System.out.println("Issue ID: " + item.getId() + " Автор: " + item.getAuthor());
        assertIterableEquals(expected, actual);
    }

    //3. Проверка обязательной функции: Фильтрация по Assignee
    @Test
    void shouldFilterByAssignee() {
        Set<String> assignee = new HashSet(Arrays.asList("Jeday"));
        Collection<Issue> actual = manager.filterByAssignee(a -> a.equals(assignee));
        Collection<Issue> expected = List.of(second, forth);
        for (Issue item : actual)
            System.out.println("Issue ID: " + item.getId() + " Кому назначено: " + item.getAssignee());
        assertIterableEquals(expected, actual);
    }

    //3. Проверка обязательной функции: Фильтрация по Label(тег)
    @Test
    void shouldFilterByLabel() {
        Set<String> label = new HashSet(Arrays.asList("Red"));
        Collection<Issue> actual = manager.filterByLabel(a -> a.equals(label));
        Collection<Issue> expected = List.of(first, forth);
        for (Issue item : actual)
            System.out.println("Issue ID: " + item.getId() + " Тег: " + item.getLabel());
        assertIterableEquals(expected, actual);
    }

    // 4. Проверка обязательной функции:  Сортировка по ID (по возрастанию)
    @Test
    void shouldSortCollectionById() {
        Collection<Issue> actual = manager.sortById();
        Collection<Issue> expected = List.of(first, second, third, forth);
        for (Issue item : actual)
            System.out.println("Issue ID: " + item.getId());
        assertIterableEquals(expected, actual);
    }

    // 4. Проверка обязательной функции:  Сортировка по Дате создания Issue (сначала старые)
    @Test
    void shouldSortCollectionByOlderCreationDate() {
        Collection<Issue> actual = manager.sortByCreationDate(olderCreationDate);
        Collection<Issue> expected = List.of(first, second, third, forth);
        for (Issue item : actual)
            System.out.println("Issue ID: " + item.getId() + ", Дата создания Issue: " + dateFormatting(item.getCreationDate(), "yyyy.MM.dd"));
        assertIterableEquals(expected, actual);
    }

    // 4. Проверка обязательной функции:  Сортировка по Дате создания Issue (сначала новые)
    @Test
    void shouldSortCollectionByRecentCreationDate() {
        Collection<Issue> actual = manager.sortByCreationDateReverse(recentCreationDate);
        Collection<Issue> expected = List.of(forth, third, second, first);
        for (Issue item : actual)
            System.out.println("Issue ID: " + item.getId() + ", Дата создания Issue: " + dateFormatting(item.getCreationDate(), "yyyy.MM.dd"));
        assertIterableEquals(expected, actual);
    }

    // 4. Проверка обязательной функции:  Сортировка по Дате обновления Issue (сначала старые)
    @Test
    void shouldSortCollectionByOlderUpdate() {
        Collection<Issue> actual = manager.sortByUpdate(olderUpdate);
        Collection<Issue> expected = List.of(second, first, third, forth);
        for (Issue item : actual)
            System.out.println("Issue ID: " + item.getId() + ", Дата обновления Issue: " + dateFormatting(item.getDateOfUpdate(), "yyyy.MM.dd"));
        assertIterableEquals(expected, actual);
    }

    // 4. Проверка обязательной функции:  Сортировка по Дате обновления Issue (сначала новые)
    @Test
    void shouldSortCollectionByRecentUpdate() {
        Collection<Issue> actual = manager.sortByUpdateReverse(recentUpdate);
        Collection<Issue> expected = List.of(forth, first, third, second);
        for (Issue item : actual)
            System.out.println("Issue ID: " + item.getId() + ", Дата обновления Issue: " + dateFormatting(item.getDateOfUpdate(), "yyyy.MM.dd"));
        assertIterableEquals(expected, actual);
    }

    // 5. Проверка обязательной функции: Закрытие Открытого Issue по id
    @Test
    void shouldCloseIssueWhenOpened() {
        int idToCheck = 1;
        manager.closeByID(idToCheck);
        boolean actual = manager.getRepository().findById(idToCheck).isClosedIssue();
        boolean expected = closed;
        assertEquals(actual, expected);
    }

    // 5. Проверка обязательной функции: Попытка закрыть по id Issue, который уже закрыт
    @Test
    void shouldNotCloseIssueWhenAlreadyClosed() {
        int idToCheck = 3;
        manager.closeByID(idToCheck);
        boolean actual = manager.getRepository().findById(idToCheck).isClosedIssue();
        boolean expected = closed;
        assertEquals(actual, expected);
    }

    // 5. Проверка обязательной функции: Закрытие Issue по id (выкидывает NotFoundException если такого ID нет)
    @Test
    void shouldGenerateExceptionWhenTryToRemoveMissingElement() {
        int idToCheck = 7;
        Exception e = assertThrows(NotFoundException.class, () -> manager.closeByID(idToCheck));
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

    // 5. Проверка обязательной функции: Открытие Закрытого Issue по id
    @Test
    void shouldOpenIssueWhenClosed() {
        int idToCheck = 4;
        manager.openByID(idToCheck);
        boolean actual = manager.getRepository().findById(idToCheck).isClosedIssue();
        boolean expected = opened;
        assertEquals(actual, expected);
    }

    // 5. Проверка обязательной функции: Попытка открыть по id Issue, который уже открыт
    @Test
    void shouldNotOpenIssueWhenAlreadyOpened() {
        int idToCheck = 2;
        manager.openByID(idToCheck);
        boolean actual = manager.getRepository().findById(idToCheck).isClosedIssue();
        boolean expected = opened;
        assertEquals(actual, expected);
    }

    // 5. Проверка обязательной функции: открытие Issue по id (выкидывает NotFoundException если такого ID нет)
    @Test
    void shouldGenerateExceptionWhenTryToRemoveMissingIssue() {
        int idToCheck = 6;
        Exception e = assertThrows(NotFoundException.class, () -> manager.openByID(idToCheck));
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
