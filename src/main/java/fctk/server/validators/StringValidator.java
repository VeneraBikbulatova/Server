package fctk.server.validators;

import java.util.List;

public class StringValidator{

    public boolean isNotEmpty(String s,
                              List<String> errorList,
                              String fieldName,
                              String errorMessage){
        if (s == null || s.isEmpty()){
            errorList.add(fieldName + ": " + errorMessage);
            return false;
        }
        return true;
    }

    public boolean isNotTooLong(String s,
                                int max,
                                List<String> errorList,
                                String fieldName,
                                String errorMessage){
        if (s.length()>max){
            errorList.add(fieldName + ": " + errorMessage);
            return false;
        }
        return true;
    }

}
