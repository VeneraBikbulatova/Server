package fctk.server.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class AddStudentRequest {
    private String firstname;
    private String middlename;
    private String lastname;
    private long groupId;
    private long id;
    private String status;

    public AddStudentRequest() {}
    public AddStudentRequest(String firstname, String middlename, String lastname, long groupId, long id, String status) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.groupId = groupId;
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

    public long getGroupId() {
        return groupId;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
