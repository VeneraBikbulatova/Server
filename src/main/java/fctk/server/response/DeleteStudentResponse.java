package fctk.server.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DeleteStudentResponse {
    private long id;
    @JsonCreator
    public DeleteStudentResponse(@JsonProperty("id") long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteStudentResponse that = (DeleteStudentResponse) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeleteStudentResponse{" +
                "id=" + id +
                '}';
    }
}
