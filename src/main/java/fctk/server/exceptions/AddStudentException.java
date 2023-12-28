package fctk.server.exceptions;

public class AddStudentException extends Exception{
    private String message = "Add student exception";
    public String getMessage(){
        return message;
    }
}
