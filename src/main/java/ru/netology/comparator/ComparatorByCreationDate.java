package ru.netology.comparator;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.Comparator;

@Data
public class ComparatorByCreationDate implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
}

