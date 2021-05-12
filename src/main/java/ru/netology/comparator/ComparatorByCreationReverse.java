package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class ComparatorByCreationReverse implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getCreationDate().compareTo(o1.getCreationDate());
    }
}
