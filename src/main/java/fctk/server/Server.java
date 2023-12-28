package fctk.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fctk.server.controllers.StudentController;
import fctk.server.exceptions.ServerException;
import fctk.server.handlers.*;
import fctk.server.repository.DataBase;
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

    public void init(){
        dataBase = new DataBase();
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
                deleteStudentRequestValidator
                );
        //add handlers
        map.put("addStudent", new AddStudentHandler(studentController));
        map.put("editStudent", new EditStudentHandler(studentController));
        map.put("deleteStudent", new DeleteStudentHandler(studentController));
        map.put("getStudentById", new GetStudentByIdHandler(studentController));
        map.put("getStudentsByGroup", new GetStudentsByGroupHandler(studentController));
    }

    public String execute(String json, String endpoint) throws ServerException {
        init();
        IHandler handler = map.get(endpoint);
        return handler.handle(json);
/*        switch (endpoint) {
            case "addStudent" :
                try {
                    ResponseEntity<CommonResponse<AddStudentResponse>> res = studentController.
                            addStudent(objectMapper.readValue(json, AddStudentRequest.class));
                    return objectMapper.writeValueAsString(res);
                } catch (Exception e) {
                    throw new ServerException();
                }
            break;
            case "editStudent" :
                try {
                    ResponseEntity<CommonResponse<Void>> res = studentController.
                            editStudent(objectMapper.readValue(json, EditStudentRequest.class));
                } catch (IOException e) {
                    throw new ServerException();
                }
                break;
            case "deleteStudent" :
                try {
                    ResponseEntity<CommonResponse<Void>> res = studentController.
                            delStudentById(objectMapper.readValue(json, DeleteStudentRequest.class));
                } catch (IOException e) {
                    throw new ServerException();
                }
                break;
            case "getStudentById" :
                try {
                    ResponseEntity<CommonResponse<GetStudentByIdResponse>> res = studentController.
                            getStudentsById(objectMapper.readValue(json, GetStudentByIdRequest.class));
                } catch (IOException e) {
                    throw new ServerException();
                }
                break;
            case "getStudentByGroup" :
                try {
                    ResponseEntity<CommonResponse<GetStudentsByGroupResponse>> res = studentController.
                            getStudentsByGroup(objectMapper.readValue(json, GetStudentsByGroupRequest.class));
                } catch (IOException e) {
                    throw new ServerException();
                }
                break;
            default: throw new ServerException();
        }

  */
    }
}
