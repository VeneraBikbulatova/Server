package fctk.server.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fctk.server.entity.StudentDB;

import java.beans.ConstructorProperties;
import java.util.Objects;

@JsonDeserialize
public class AddStudentResponse {
    private long id;
    private StudentDB studentDB;
    @JsonCreator
    public AddStudentResponse(@JsonProperty("id") long id, @JsonProperty("studentDB") StudentDB studentDB) {
        this.id = id;
        this.studentDB = studentDB;
    }

    public long getId() {
        return id;
    }

    public StudentDB getStudentDB() {
        return studentDB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddStudentResponse that = (AddStudentResponse) o;
        return id == that.id && Objects.equals(studentDB, that.studentDB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentDB);
    }

    @Override
    public String toString() {
        return "AddStudentResponse{" +
                "id=" + id +
                ", studentDB=" + studentDB +
                '}';
    }
}
