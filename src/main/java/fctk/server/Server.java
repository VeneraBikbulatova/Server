package fctk.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fctk.server.controllers.StudentController;
import fctk.server.exceptions.ServerException;
import fctk.server.handlers.*;
import fctk.server.repository.DataBase;
import fctk.server.repository.StudentRepository;
import fctk.server.services.StudentService;
import fctk.server.validators.*;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize
public class Server implements IServer{
    private StudentController studentController;
    private DataBase dataBase;
    private static Map<String, IHandler> map = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private AddStudentRequestValidator addStudentRequestValidator;
    private EditStudentRequestValidator editStudentRequestValidator;
    private DeleteStudentRequestValidator deleteStudentRequestValidator;
    private GetStudentByIdRequestValidator getStudentByIdRequestValidator;
    private GetStudentByGroupRequestValidator getStudentByGroupRequestValidator;

    private StringValidator stringValidator;
    private IdValidator idValidator;

    public Server(){
        this.dataBase = new DataBase();
        init();
    }

    public Server(DataBase dataBase) {
        this.dataBase = dataBase;
        init();
    }

    public void init(){
        stringValidator = new StringValidator();
        idValidator = new IdValidator();
        addStudentRequestValidator = new AddStudentRequestValidator(stringValidator, idValidator);
        editStudentRequestValidator = new EditStudentRequestValidator(stringValidator, idValidator);
        deleteStudentRequestValidator = new DeleteStudentRequestValidator(idValidator);
        getStudentByIdRequestValidator = new GetStudentByIdRequestValidator(idValidator);
        getStudentByGroupRequestValidator = new GetStudentByGroupRequestValidator(idValidator);
        studentController = new StudentController(addStudentRequestValidator,
                editStudentRequestValidator,
                getStudentByIdRequestValidator,
                getStudentByGroupRequestValidator,
                deleteStudentRequestValidator,
                new StudentService(new StudentRepository(dataBase))
                );
        //add handlers
        map.put("addStudent", new AddStudentHandler(studentController));
        map.put("editStudent", new EditStudentHandler(studentController));
        map.put("deleteStudent", new DeleteStudentHandler(studentController));
        map.put("getStudentById", new GetStudentByIdHandler(studentController));
        map.put("getStudentsByGroup", new GetStudentsByGroupHandler(studentController));
    }

    public String execute(String json, String endpoint) throws ServerException {
        IHandler handler = map.get(endpoint);
        return handler.handle(json);
    }
}
