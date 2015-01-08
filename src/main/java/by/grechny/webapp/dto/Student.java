package by.grechny.webapp.dto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private Set<Mark> marks;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId () {
        return this.id;
    }

    public void setId (int id){
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName () {
        return this.firstName;
    }

    public void setFirstName (String firstName){
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName () {
        return this.lastName;
    }

    public void setLastName (String lastName){
        this.lastName = lastName;
    }

    public void setValues (int id, String firstName, String lastName) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
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
        if (!(object instanceof Student))return false;
        Student student = (Student)object;
        return (student.getId() == this.id) && (student.getFirstName().equals(this.firstName))
                && (student.getLastName().equals(this.lastName));
    }
}
