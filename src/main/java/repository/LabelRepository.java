package repository;

import domain.Issue;
import domain.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LabelRepository {
    private Collection<Label> labels = new HashSet<>();

    public void save (Label label) {
        labels.add(label);
    }

    public Collection<Label> findAll() {
        return labels;
    }
}
