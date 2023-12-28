package fctk.server.validators;

import fctk.server.request.AddStudentRequest;

import java.util.ArrayList;
import java.util.List;

public class AddStudentRequestValidator
        implements Validator<AddStudentRequest>{
    StringValidator stringValidator;
    IdValidator idValidator;

    public AddStudentRequestValidator(StringValidator stringValidator, IdValidator idValidator) {
        this.stringValidator = stringValidator;
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(AddStudentRequest addStudentRequest) {
        List<String> errorList = new ArrayList<>();
        stringValidator.isNotEmpty(
                addStudentRequest.getFirstname(),
                errorList,
                "firstName",
                "should be not empty");
        stringValidator.isNotTooLong(
                addStudentRequest.getFirstname(),
                1000,
                errorList,
                "firstName",
                "should be shorter"
        );
        stringValidator.isNotEmpty(
                addStudentRequest.getMiddlename(),
                errorList,
                "middleName",
                "should be not empty");
        stringValidator.isNotTooLong(
                addStudentRequest.getMiddlename(),
                1000,
                errorList,
                "middleName",
                "should be shorter"
        );
        stringValidator.isNotEmpty(
                addStudentRequest.getLastname(),
                errorList,
                "lastName",
                "should be not empty");
        stringValidator.isNotTooLong(
                addStudentRequest.getLastname(),
                1000,
                errorList,
                "lastName",
                "should be shorter"
        );
        idValidator.isPositive(
                addStudentRequest.getGroupId(),
                errorList,
                "groupId",
                "should be positive");
        stringValidator.isNotEmpty(
                addStudentRequest.getStatus(),
                errorList,
                "status",
                "should be not empty");
        stringValidator.isNotTooLong(
                addStudentRequest.getStatus(),
                100,
                errorList,
                "status",
                "should be shorter"
        );

        return errorList;
    }
}
