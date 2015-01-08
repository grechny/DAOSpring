package by.grechny.webapp.dto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    private int id;
    private String subject;
    private Set<Mark> marks;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId () {
        return this.id;
    }

    public void setId (int id) {
        this.id = id;
    }

    @Column(name = "subject")
    public String getSubject () {
        return this.subject;
    }

    public void setSubject (String subject) {
        this.subject = subject;
    }

    public void setValues (int id, String subject) {

        this.id = id;
        this.subject = subject;

    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks){
        this.marks = marks;
    }

    @Override
    public boolean equals(Object object){
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof Subject))return false;
        Subject subject = (Subject)object;
        return (subject.getId() == this.id) && (subject.getSubject().equals(this.subject));
    }
}


