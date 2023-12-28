package fctk.server.validators;

import fctk.server.request.EditStudentRequest;

import java.util.ArrayList;
import java.util.List;

public class EditStudentRequestValidator
        implements Validator<EditStudentRequest>{
    private StringValidator stringValidator;
    private IdValidator idValidator;

    public EditStudentRequestValidator(StringValidator stringValidator, IdValidator idValidator) {
        this.stringValidator = stringValidator;
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(EditStudentRequest editStudentRequest) {
        List<String> errorList = new ArrayList<>();
        stringValidator.isNotEmpty(
                editStudentRequest.getFirstname(),
                errorList,
                "firstName",
                "should be not empty");
        stringValidator.isNotTooLong(
                editStudentRequest.getFirstname(),
                1000,
                errorList,
                "firstName",
                "should be shorter"
        );
        stringValidator.isNotEmpty(
                editStudentRequest.getMiddlename(),
                errorList,
                "middleName",
                "should be not empty");
        stringValidator.isNotTooLong(
                editStudentRequest.getMiddlename(),
                1000,
                errorList,
                "middleName",
                "should be shorter"
        );
        stringValidator.isNotEmpty(
                editStudentRequest.getLastname(),
                errorList,
                "lastName",
                "should be not empty");
        stringValidator.isNotTooLong(
                editStudentRequest.getLastname(),
                1000,
                errorList,
                "lastName",
                "should be shorter"
        );
        idValidator.isPositive(
                editStudentRequest.getGroupId(),
                errorList,
                "groupId",
                "should be not empty");
        stringValidator.isNotEmpty(
                editStudentRequest.getStatus(),
                errorList,
                "status",
                "should be not empty");
        stringValidator.isNotTooLong(
                editStudentRequest.getStatus(),
                100,
                errorList,
                "status",
                "should be shorter"
        );
        idValidator.isPositive(
                editStudentRequest.getId(),
                errorList,
                "id",
                "should be more 0"
        );
        return errorList;
    }
}
