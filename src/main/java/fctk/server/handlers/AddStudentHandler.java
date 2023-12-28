package fctk.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fctk.server.controllers.StudentController;
import fctk.server.request.AddStudentRequest;
import fctk.server.response.AddStudentResponse;
import fctk.server.response.CommonResponse;
import fctk.server.response.ResponseEntity;

import java.io.IOException;

@JsonDeserialize
public class AddStudentHandler implements IHandler {
    private StudentController studentController;

    public AddStudentHandler(StudentController studentController) {
        this.studentController = studentController;
    }
    @Override
    public String handle(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            AddStudentRequest request = mapper.readValue(requestBody, AddStudentRequest.class);
            ResponseEntity<CommonResponse<AddStudentResponse>> res = studentController.
                    addStudent(request);
            return mapper.writeValueAsString(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
