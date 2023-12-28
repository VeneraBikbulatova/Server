package fctk.server.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteStudentRequest {
    private long id;

    public DeleteStudentRequest(){
        id = 0;
    }

    @JsonCreator
    public DeleteStudentRequest(@JsonProperty("id") long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
