package by.grechny.webapp.dto;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {

    private int id;
    private Student studentId;
    private Subject subjectId;
    private int mark;

    @Id
    @Column(name = "mark_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId () {
        return this.id;
    }

    public void setId (int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id" )
    public Student getStudentId () {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    public Subject getSubjectId () {
        return this.subjectId;
    }

    public void setSubjectId (Subject subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "mark")
    public int getMark() {
        return this.mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setValues (int id, Student studentId, Subject subjectId, int mark) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.mark = mark;
    }
}
