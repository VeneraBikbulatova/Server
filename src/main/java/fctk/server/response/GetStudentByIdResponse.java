package fctk.server.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fctk.server.entity.Student;
import fctk.server.entity.StudentDB;

import java.util.Objects;

@JsonDeserialize
public class GetStudentByIdResponse {
    private long id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String group;
    private String status;

    @JsonCreator
    public GetStudentByIdResponse(@JsonProperty("id") long id,
                                  @JsonProperty("lastname") String lastname,
                                  @JsonProperty("firstname") String firstname,
                                  @JsonProperty("middlename") String middlename,
                                  @JsonProperty("group") String group,
                                  @JsonProperty("status") String status) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.group = group;
        this.status = status;

    }

    public long getId() {
        return id;
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

    public String getGroup() {
        return group;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetStudentByIdResponse that = (GetStudentByIdResponse) o;
        return id == that.id && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(middlename, that.middlename) && Objects.equals(group, that.group) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, middlename, group, status);
    }

    @Override
    public String toString() {
        return "GetStudentByIdResponse{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", group='" + group + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
