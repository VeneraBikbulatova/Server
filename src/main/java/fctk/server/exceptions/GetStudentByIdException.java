package fctk.server.exceptions;

public class GetStudentByIdException extends Exception{
    private String message;
    public String getMessage(){
        return message;
    }
}
