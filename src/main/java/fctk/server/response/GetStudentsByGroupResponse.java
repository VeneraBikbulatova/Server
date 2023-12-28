package fctk.server.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fctk.server.entity.Student;

import java.util.List;
import java.util.Objects;

public class GetStudentsByGroupResponse {
    private List<Student> list;

    @JsonCreator
    public GetStudentsByGroupResponse(@JsonProperty("list") List<Student> list) {
        this.list = list;
    }

    public List<Student> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetStudentsByGroupResponse that = (GetStudentsByGroupResponse) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "GetStudentsByGroupResponse{" +
                "list=" + list +
                '}';
    }
}
