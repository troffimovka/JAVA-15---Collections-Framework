package manager;

import domain.Issue;
import domain.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.IssueRepository;
import repository.LabelRepository;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    IssueManager manager = new IssueManager(new IssueRepository());
    Label label = new Label();
    //LabelRepository labelRepository = new LabelRepository();

    Label labelFirst = new Label(1, "component", "green");
    Label labelSecond = new Label(2, "status", "red");
    Label labelThird = new Label(3, "theme", "grey");
    Label labelForth = new Label(4, "type", "purple");
    Label labelFifth = new Label(5, "theme", "blue");

    Issue issueFirst = new Issue(1,
            new HashSet<>(Arrays.asList(labelFirst, labelSecond, labelThird)),
            new GregorianCalendar(2018, Calendar.JANUARY, 12),
            "Add attribute", "junit-buildmaster", "Twonki", false, 30, 15);
    Issue issueSecond = new Issue(2,
            new HashSet<>(Arrays.asList(labelFirst, labelThird)),
            new GregorianCalendar(2020, Calendar.MARCH, 8),
            "Introduce API", "null", "frascu", false, 25, 11);
    Issue issueThird = new Issue(3,
            new HashSet<>(Arrays.asList(labelFirst, labelForth, labelFifth)),
            new GregorianCalendar(2021, Calendar.MARCH, 25),
            "Implementation of assertion", "junit-buildmaster", "Twonki", true, 2, 0);
    Issue issueForth = new Issue(4,
            new HashSet<>(Collections.singletonList(labelFirst)),
            new GregorianCalendar(2019, Calendar.DECEMBER, 31),
            "Console Standalone", "null", "Twonki", false, 12, 0);
    Issue issueFifth = new Issue(5,
            new HashSet<>(Collections.emptyList()),
            new GregorianCalendar(2021, Calendar.FEBRUARY, 28),
            "Providing more options", "null", "Twonki", true, 5, 5);
    Issue issueSixth = new Issue(6,
            new HashSet<>(Arrays.asList(labelFirst, labelFifth)),
            new GregorianCalendar(2020, Calendar.JUNE, 12),
            "get all configuration", "junit-buildmaster", "Phlegethonyarre", false, 3, 1);
    Issue issueSeventh = new Issue(7,
            new HashSet<>(Collections.singletonList(labelThird)),
            new GregorianCalendar(2021, Calendar.FEBRUARY, 8),
            "Add assumptions", "junit-buildmaster", "Twonki", true, 3, 1);
    Issue issueEightth = new Issue(8,
            new HashSet<>(Arrays.asList(labelSecond, labelThird)),
            new GregorianCalendar(2020, Calendar.NOVEMBER, 4),
            "Make default attribute", "junit-buildmaster", "jlink", true, 0, 0);
    Issue issueNineth = new Issue(9,
            new HashSet<>(Arrays.asList(labelFirst, labelFifth, labelSecond, labelThird, labelForth)),
            new GregorianCalendar(2019, Calendar.SEPTEMBER, 1),
            "Java doc generation", "null", "DaeAkin ", false, 10, 0);
    Issue issueTenth = new Issue(10,
            new HashSet<>(Arrays.asList(labelFirst,labelThird)),
            new GregorianCalendar(2018, Calendar.JANUARY, 12),
            "Support custom", "sonmuras", "Twonki", true, 8, 15);

    @BeforeEach
    void setUp() {
        manager.add(issueFirst);
        manager.add(issueSecond);
        manager.add(issueThird);
        manager.add(issueForth);
        manager.add(issueFifth);
        manager.add(issueSixth);
        manager.add(issueSeventh);
        manager.add(issueEightth);
        manager.add(issueNineth);
        manager.add(issueTenth);
    }



    @Test
    void shouldGetAllAfterAdding() {
        Issue[] expected = {issueFirst, issueSecond, issueThird, issueForth, issueFifth, issueSixth, issueSeventh, issueEightth, issueNineth, issueTenth};
        Issue[] actual = manager.getAll().toArray(new Issue[0]);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAllOppened() {
        Issue[] expected = {issueThird, issueFifth, issueSeventh, issueEightth, issueTenth};
        Issue[] actual = manager.findAllOppened().toArray(new Issue[0]);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAllClosed() {
        Issue[] expected = {issueFirst, issueSecond, issueForth, issueSixth, issueNineth};
        Issue[] actual = manager.findAllClosed().toArray(new Issue[0]);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAuthor() {
        Issue[] expected = {issueFirst, issueThird, issueForth, issueFifth, issueSeventh, issueTenth};
        Issue[] actual = manager.findByAuthor("Twonki").toArray(new Issue[0]);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAssignee() {
        Issue[] expected = {issueFirst, issueThird, issueSixth, issueSeventh, issueEightth};
        Issue[] actual = manager.findByAssignee("junit-buildmaster").toArray(new Issue[0]);
        assertArrayEquals(expected, actual);
    }

    @Test
    void findByLabel() {
    }

    @Test
    void findByAssignee() {
    }
}