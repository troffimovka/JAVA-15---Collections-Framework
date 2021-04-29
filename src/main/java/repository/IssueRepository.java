package repository;

import domain.Issue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class IssueRepository {
    private Collection<Issue> issues = new ArrayList<>();

       public void save (Issue issue) {
           issues.add(issue);
       }

       public Collection<Issue> findAll() {
           return issues;
       }

    public void findAllOpened() {
           issues.removeIf(i -> !i.isOpen());
    }

    public void findAllClosed() {
        issues.removeIf(Issue::isOpen);
    }

    public void Search(String author) {
           issues.stream().sorted();
    }

    public void findByAuthor(String author) {
        issues.removeIf(i -> !i.getAuthor().equals(author));
    }

   // public void findByLabel(String label) {
     //   issues.removeIf(i -> i.getLabel() != label);
    //}

    public void findByAssignee(String assignee) {
        issues.removeIf(i -> !i.getAssignee().equals(assignee));
    }
}
