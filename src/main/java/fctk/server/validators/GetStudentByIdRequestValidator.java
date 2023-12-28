package fctk.server.validators;

import fctk.server.request.GetStudentByIdRequest;

import java.util.ArrayList;
import java.util.List;

public class GetStudentByIdRequestValidator
        implements Validator<GetStudentByIdRequest>{
    IdValidator idValidator;

    public GetStudentByIdRequestValidator(IdValidator idValidator) {
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(GetStudentByIdRequest request) {
        List<String> errors = new ArrayList<>();
        idValidator.isPositive(
                request.getId(),
                errors,
                "id",
                "should be positive"
        );
        return errors;
    }
}
