package fctk.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.controllers.StudentController;
import fctk.server.request.GetStudentByIdRequest;
import fctk.server.response.CommonResponse;
import fctk.server.response.GetStudentByIdResponse;
import fctk.server.response.ResponseEntity;

import java.io.IOException;

public class GetStudentByIdHandler implements IHandler {
    private StudentController studentController;

    public GetStudentByIdHandler(StudentController studentController) {
        this.studentController = studentController;
    }
    @Override
    public String handle(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            GetStudentByIdRequest request = mapper.readValue(requestBody, GetStudentByIdRequest.class);
            ResponseEntity<CommonResponse<GetStudentByIdResponse>> res = studentController.
                    getStudentsById(request);
            return mapper.writeValueAsString(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
