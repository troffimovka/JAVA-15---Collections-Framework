package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Issue implements Comparable<Issue>{
    private Calendar date = new GregorianCalendar();

    private int id;
    private Collection<Label> labels;
    public Issue(int id, Collection<Label> labels, GregorianCalendar date, String name, String assignee, String author, boolean open, int numberOfCom, int numberOfReact) {
        this.id = id;
        this.labels = labels;
        this.date = date;
        this.name = name;
        this.assignee = assignee;
        this.author = author;
        this.open = open;
        this.numberOfCom = numberOfCom;
        this.numberOfReact = numberOfReact;
    }

    //private final long dateOfadding = date.getTime();

    private String name;
    private String project;
    private String milestone;
    ///private Collection<Label> issueLabels;
    private String tag;
    private String assignee;
    private String author;
    private boolean open;
    private int numberOfCom;
    private int numberOfReact;

    @Override
    public int compareTo(Issue o)
    {
        if (o.date.getTimeInMillis() > this.date.getTimeInMillis())
            return -1;
        else
            return 1;
    }
}
