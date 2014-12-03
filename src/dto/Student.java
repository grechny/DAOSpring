package dto;

public class Student {

    int id;
    String firstName;
    String lastName;

    public void setValues (int id, String firstName, String lastName) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public void setID (int id){
      this.id = id;
    }

    public void setFirstName (String firstName){
          this.firstName = firstName;
    }

    public void setLastName (String lastName){
        this.lastName = lastName;
    }

    public int getId () {
        return this.id;
    }

    public String getFirstName () {
        return this.firstName;
    }

    public String getLastName () {
        return this.lastName;
    }

}
