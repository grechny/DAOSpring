package dto;

public class Subject {

    int id;
    String subject;

    public void setValues (int id, String subject) {

        this.id = id;
        this.subject = subject;

    }

    public void setId (int id) {
        this.id = id;
    }

    public void setSubject (String subject) {
        this.subject = subject;
    }

    public int getId () {
        return this.id;
    }

    public String getSubject () {
        return this.subject;
    }

}


