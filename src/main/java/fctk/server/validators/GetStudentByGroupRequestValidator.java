package fctk.server.validators;

import fctk.server.request.GetStudentsByGroupRequest;

import java.util.ArrayList;
import java.util.List;

public class GetStudentByGroupRequestValidator
        implements Validator<GetStudentsByGroupRequest>{

    IdValidator idValidator;

    public GetStudentByGroupRequestValidator(IdValidator idValidator) {
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(GetStudentsByGroupRequest request) {
        List<String> errors=  new ArrayList<>();
        idValidator.isPositive(
                request.getGroupId(),
                errors,
                "groupId",
                "should be positive");
        return errors;
    }
}
