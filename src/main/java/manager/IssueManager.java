package manager;

import domain.Issue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import repository.IssueRepository;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IssueManager {
    IssueRepository repository = new IssueRepository();

    public void add(Issue issue) {
        repository.save(issue);
    }

    public Collection<Issue> getAll() {
        return repository.findAll();

    }


    public Collection<Issue> findAllOppened() {
        repository.findAllOpened();
        return repository.getIssues();
    }

    public Collection<Issue> findAllClosed() {
        repository.findAllClosed();
        return repository.getIssues();
    }

    public void updateById() {
        // закрытие/ открытие по Id
    }

    public Collection<Issue> findByAuthor(String author) {
        repository.findByAuthor(author);
        return repository.getIssues();
    }

   // public Collection<Issue> findByLabel(String label) {
     //   repository.findByLabel(label);
       // return repository.getIssues();
    //}

    public Collection<Issue> findByAssignee(String assignee) {
        repository.findByAssignee(assignee);
        return repository.getIssues();
    }

    public Collection<Issue> filterByAssignee (String assignee) {
        return repository.getIssues();
    }

    //    public Offer[] findByAirport(String to, String from, Comparator<Offer> comparator) {
//        Offer[] result = new Offer[0];
//        for (Offer offer : repository.findAll()) {
//            if (offer.getTo().equals(to) & offer.getFrom().equals(from)) {
//                Offer[] tmp = new Offer[result.length + 1];
//                System.arraycopy(result, 0, tmp, 0, result.length);
//                tmp[tmp.length - 1] = offer;
//                result = tmp;
//            }
//        }
//        Arrays.sort(result, comparator);
//        return result;
//    }
}
