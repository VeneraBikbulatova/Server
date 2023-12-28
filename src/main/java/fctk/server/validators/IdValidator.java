package fctk.server.validators;

import java.util.List;

public class IdValidator {
    public boolean isPositive(long id,
                              List<String> errorList,
                              String fieldName,
                              String errorMessage){
        if(id <= 0){
            errorList.add(fieldName + ": " + errorMessage);
            return false;
        }
        return true;
    }



}
