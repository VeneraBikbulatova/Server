package fctk.server.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fctk.server.entity.StudentDB;
import fctk.server.request.*;
import fctk.server.response.*;
import fctk.server.services.StudentService;
import fctk.server.validators.*;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize
public class StudentController {
    private Validator<AddStudentRequest> addStudentRequestValidator;
    private Validator<EditStudentRequest> editStudentRequestValidator;
    private Validator<GetStudentByIdRequest> getStudentByIdRequestValidator;
    private Validator<GetStudentsByGroupRequest> getStudentsByGroupRequestValidator;
    private Validator<DeleteStudentRequest> deleteStudentRequestValidator;
    private StudentService studentService;

    public StudentController(Validator<AddStudentRequest> addStudentRequestValidator,
                             Validator<EditStudentRequest> editStudentRequestValidator,
                             Validator<GetStudentByIdRequest> getStudentByIdRequestValidator,
                             Validator<GetStudentsByGroupRequest> getStudentsByGroupRequestValidator,
                             Validator<DeleteStudentRequest> deleteStudentRequestValidator,
                             StudentService studentService) {
        this.addStudentRequestValidator = addStudentRequestValidator;
        this.editStudentRequestValidator = editStudentRequestValidator;
        this.getStudentByIdRequestValidator = getStudentByIdRequestValidator;
        this.getStudentsByGroupRequestValidator = getStudentsByGroupRequestValidator;
        this.deleteStudentRequestValidator = deleteStudentRequestValidator;
        this.studentService = studentService;
    }

    public ResponseEntity<CommonResponse<AddStudentResponse>> addStudent(AddStudentRequest addStudentRequest) {
        int httpStatus;
        CommonResponse<AddStudentResponse> commonResponse;
        try {
            List<String> errors = addStudentRequestValidator.validate(addStudentRequest);
            if (errors.isEmpty()) {
                commonResponse =
                        new CommonResponse<>(new AddStudentResponse(addStudentRequest.getGroupId(), new StudentDB(
                                addStudentRequest.getLastname(),
                                addStudentRequest.getFirstname(),
                                addStudentRequest.getMiddlename(),
                                addStudentRequest.getGroupId(),
                                addStudentRequest.getId(),
                                addStudentRequest.getStatus()
                        )));
                studentService.addStudent(commonResponse.getData().getStudentDB());
                httpStatus = 201;
            } else {
                commonResponse =
                        new CommonResponse<>(1, "Validation error", errors);
                httpStatus = 422;
            }
            return new ResponseEntity<>(commonResponse, httpStatus);
        } catch (Exception exception){
            return new ResponseEntity<>(new CommonResponse<AddStudentResponse>(null),400);
        }
    }

    public ResponseEntity<CommonResponse<GetStudentByIdResponse>> getStudentsById(GetStudentByIdRequest getStudentByIdRequest){
        int httpStatus;
        CommonResponse<GetStudentByIdResponse> commonResponse;
        try {
            List<String> errors = getStudentByIdRequestValidator.validate(getStudentByIdRequest);
            if (errors.isEmpty()) {
                var student = studentService.getStudentById(getStudentByIdRequest.getId());
                commonResponse =
                        new CommonResponse<>(new GetStudentByIdResponse(student.getId(),
                                student.getLastname(),
                                student.getFirstname(),
                                student.getMiddlename(),
                                student.getGroup(),
                                student.getStatus()));
                httpStatus = 201;
            } else {
                commonResponse =
                        new CommonResponse<>(1, "Validation error", errors);
                httpStatus = 422;
            }
            return new ResponseEntity<>(commonResponse, httpStatus);
        } catch (Exception exception) {
            return new ResponseEntity<>(new CommonResponse<GetStudentByIdResponse>(null), 400);
        }
    }

    public ResponseEntity<CommonResponse<GetStudentsByGroupResponse>> getStudentsByGroup(GetStudentsByGroupRequest getStudentsByGroupRequest){
        int httpStatus;
        CommonResponse<GetStudentsByGroupResponse> commonResponse;
        try {
            List<String> errors = getStudentsByGroupRequestValidator.validate(getStudentsByGroupRequest);
            if (errors.isEmpty()) {
                commonResponse =
                        new CommonResponse<>(
                                new GetStudentsByGroupResponse(
                                        new ArrayList<>()
                                )
                        );
                studentService.getStudentsByGroup(commonResponse.getData().getList().get(0).getGroup().getGroupId());
                httpStatus = 201;
            } else {
                commonResponse =
                        new CommonResponse<>(1, "Validation error", errors);
                httpStatus = 422;
            }
            return new ResponseEntity<>(commonResponse, httpStatus);
        } catch (Exception exception) {
            return new ResponseEntity<>(new CommonResponse<GetStudentsByGroupResponse>(null), 400);
        }
    }

    public ResponseEntity<CommonResponse<Void>> delStudentById(DeleteStudentRequest deleteStudentRequest) {
        int httpStatus;
        CommonResponse<Void> commonResponse;
        try {
            List<String> errors = deleteStudentRequestValidator.validate(deleteStudentRequest);
            if (errors.isEmpty()) {
                commonResponse =
                        new CommonResponse<>(null);
                httpStatus = 201;
                studentService.deleteStudent(deleteStudentRequest.getId());
            } else {
                commonResponse =
                        new CommonResponse<>(1, "Validation error", errors);
                httpStatus = 422;
            }
            return new ResponseEntity<>(commonResponse, httpStatus);
        } catch (Exception exception) {
            return new ResponseEntity<>(new CommonResponse<>(null), 400);
        }
    }
    public ResponseEntity<CommonResponse<Void>> editStudent(EditStudentRequest editStudentRequest){
        int httpStatus;
        CommonResponse<Void> commonResponse;
        try {
            List<String> errors = editStudentRequestValidator.validate(editStudentRequest);
            if (errors.isEmpty()) {
                commonResponse =
                        new CommonResponse<>(null);
                httpStatus = 201;
                studentService.editStudent(new StudentDB(
                        editStudentRequest.getLastname(),
                        editStudentRequest.getFirstname(),
                        editStudentRequest.getMiddlename(),
                        editStudentRequest.getGroupId(),
                        editStudentRequest.getId(),
                        editStudentRequest.getStatus()
                ));
            } else {
                commonResponse =
                        new CommonResponse<>(1, "Validation error", errors);
                httpStatus = 422;
            }
            return new ResponseEntity<>(commonResponse, httpStatus);
        } catch (Exception exception) {
            return new ResponseEntity<>(new CommonResponse<>(null), 400);
        }
    }
}
