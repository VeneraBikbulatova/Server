package fctk.server.exceptions;

public class DeleteStudentException extends Exception{
    private String message;
    public String getMessage(){
        return message;
    }
}
