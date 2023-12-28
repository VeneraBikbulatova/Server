package fctk.server.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;


public class StudentDB {

        private String lastname;
        private String firstname;
        private String middlename;
        private long groupId;
        private long id;
        private String status;
        @JsonCreator
    public StudentDB(@JsonProperty("lastname") String lastname,
                     @JsonProperty("firstname") String firstname,
                     @JsonProperty("middlename") String middlename,
                     @JsonProperty("groupId") long groupId,
                     @JsonProperty("id") long id,
                     @JsonProperty("status") String status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDB studentDB = (StudentDB) o;
        return groupId == studentDB.groupId && id == studentDB.id && Objects.equals(lastname, studentDB.lastname) && Objects.equals(firstname, studentDB.firstname) && Objects.equals(middlename, studentDB.middlename) && Objects.equals(status, studentDB.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, middlename, groupId, id, status);
    }
}


