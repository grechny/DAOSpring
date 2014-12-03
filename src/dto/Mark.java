package dto;

public class Mark {

    int id;
    int studentId;
    int subjectId;
    int mark;

    public void setValues (int id, int studentId, int subjectId, int mark) {

        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.mark = mark;

    }

    public void setId (int id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setSubjectId (int subjectId) {
        this.subjectId = subjectId;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getId () {
        return this.id;
    }

    public int getStudentId () {
        return this.studentId;
    }

    public int getSubjectId () {
        return this.subjectId;
    }

    public int getMark() {
        return this.mark;
    }
}
