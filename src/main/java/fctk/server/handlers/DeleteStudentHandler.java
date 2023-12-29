package fctk.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.controllers.StudentController;
import fctk.server.request.DeleteStudentRequest;
import fctk.server.response.CommonResponse;
import fctk.server.response.ResponseEntity;

import java.io.IOException;

public class DeleteStudentHandler implements IHandler {
    private StudentController studentController;

    public DeleteStudentHandler(StudentController studentController) {
        this.studentController = studentController;
    }
    @Override
    public String handle(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            DeleteStudentRequest request = mapper.readValue(requestBody, DeleteStudentRequest.class);
            ResponseEntity<CommonResponse<Void>> res = studentController.
                    delStudentById(request);
            return mapper.writeValueAsString(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
