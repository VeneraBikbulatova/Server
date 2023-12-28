package fctk.server.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class EditStudentRequest {
    private String lastname;
    private String firstname;
    private String middlename;
    private long groupId;
    private long id;
    private String status;

    public EditStudentRequest(){}

    public EditStudentRequest(String lastname, String firstname, String middlename, long groupId, long id, String status) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
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
