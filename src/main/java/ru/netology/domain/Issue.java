package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Issue implements Comparable<Issue> {
    private int id;
    private String name;
    private Set<String> author;
    private Set<String> label;
    private Set<String> assignee;
    private boolean closedIssue = true;
    private Calendar creationDate;
    private Calendar dateOfUpdate;

    //метод для преобразования cтроки в Коллекцию Set(Поле Автор)
    public static Set<String> setAuthor(String string1) {
        Set<String> setAuthor = new HashSet(Arrays.asList(string1));
        return setAuthor;
    }

    //метод для преобразования cтроки в Коллекцию Set(Поле Label)
    public static Set<String> setLabel(String string1) {
        Set<String> setLabel = new HashSet(Arrays.asList(string1));
        return setLabel;
    }

    //метод для преобразования cтроки в Коллекцию Set(Поле Assignee)
    public static Set<String> setAssignee(String string1) {
        Set<String> setAssignee = new HashSet(Arrays.asList(string1));
        return setAssignee;
    }

    //метод для преобразования примитивов в экземпляр класса Calendar (для полей Дата создания и Дата обновления)
    public static Calendar setDateOfCreation(int year, int month, int day) {
        Calendar setCalendar1 = new GregorianCalendar();
        setCalendar1.set(year, month, day);
        return setCalendar1;
    }

    public static Calendar setDateOfUpdate(int year, int month, int day) {
        Calendar setCalendar2 = new GregorianCalendar();
        setCalendar2.set(year, month, day);
        return setCalendar2;
    }

    @Override
    public int compareTo(Issue o) {
        return id - o.id;
    }
}

