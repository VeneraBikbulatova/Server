package fctk.server.exceptions;

public class ServerException extends Exception{
  //  private String message;

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMessage(){
        return super.getMessage();
    }

}
