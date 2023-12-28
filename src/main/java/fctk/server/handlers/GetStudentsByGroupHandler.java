package fctk.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.controllers.StudentController;
import fctk.server.request.GetStudentsByGroupRequest;
import fctk.server.response.CommonResponse;
import fctk.server.response.GetStudentsByGroupResponse;
import fctk.server.response.ResponseEntity;

import java.io.IOException;

public class GetStudentsByGroupHandler implements IHandler {
    private StudentController studentController;

    public GetStudentsByGroupHandler(StudentController studentController) {
        this.studentController = studentController;
    }

    @Override
    public String handle(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            GetStudentsByGroupRequest request = mapper.readValue(requestBody, GetStudentsByGroupRequest.class);
            ResponseEntity<CommonResponse<GetStudentsByGroupResponse>> res = studentController.
                    getStudentsByGroup(request);
            return mapper.writeValueAsString(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
