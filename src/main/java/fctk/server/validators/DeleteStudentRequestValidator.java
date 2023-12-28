package fctk.server.validators;

import fctk.server.request.DeleteStudentRequest;

import java.util.ArrayList;
import java.util.List;

public class DeleteStudentRequestValidator
        implements Validator<DeleteStudentRequest>{
    IdValidator idValidator;

    public DeleteStudentRequestValidator(IdValidator idValidator) {
        this.idValidator = idValidator;
    }

    @Override
    public List<String> validate(DeleteStudentRequest deleteStudentRequest) {
        List<String> errors = new ArrayList<>();
        idValidator.isPositive(
                deleteStudentRequest.getId(),
                errors,
                "id",
                "should be more 0");
        return errors;
    }
}
