package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class ComparatorByUpdateReverse implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getDateOfUpdate().compareTo(o1.getDateOfUpdate());
    }
}
