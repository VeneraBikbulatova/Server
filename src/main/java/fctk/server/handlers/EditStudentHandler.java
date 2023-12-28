package fctk.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.controllers.StudentController;
import fctk.server.request.EditStudentRequest;
import fctk.server.response.CommonResponse;
import fctk.server.response.EditStudentResponse;
import fctk.server.response.ResponseEntity;

import java.io.IOException;

public class EditStudentHandler implements IHandler {
    private StudentController studentController;

    public EditStudentHandler(StudentController studentController) {
        this.studentController = studentController;
    }

    @Override
    public String handle(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            EditStudentRequest request = mapper.readValue(requestBody, EditStudentRequest.class);
            ResponseEntity<CommonResponse<Void>> res = studentController.
                    editStudent(request);
            return mapper.writeValueAsString(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
