package fctk.server.entity;

public class Student {
    private String lastname;
    private String firstname;
    private String middlename;
    private Group group;
    private long id;
    private String status;

    public Student(String lastname,
                   String firstname,
                   String middlename,
                   Group group,
                   long id,
                   String status) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.group = group;
        this.id = id;
        this.status = status;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public Group getGroup() {
        return group;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
