package fctk.server.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fctk.server.Server;
import fctk.server.controllers.StudentController;
import fctk.server.entity.Group;
import fctk.server.entity.Student;
import fctk.server.entity.StudentDB;
import fctk.server.exceptions.AddStudentException;
import fctk.server.exceptions.ServerException;
import fctk.server.repository.DataBase;
import fctk.server.repository.StudentRepository;
import fctk.server.request.AddStudentRequest;
import fctk.server.request.DeleteStudentRequest;
import fctk.server.response.*;
import fctk.server.services.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class StudentsTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    static final DataBase db = new DataBase();
    static Server server;
    static String jacksonData;
    static String jacksonData1;
    static StudentDB student1;
    static StudentDB student2;
    @BeforeClass
    public static void setUp() throws JsonProcessingException {
        server = new Server();
        Map<String, Object> map = new HashMap<>();
        map.put("lastname", "last1");
        map.put("firstname", "first1");
        map.put("middlename", "middle1");
        map.put("status", "status");
        map.put("id", 1);
        map.put("groupId", 1);
        Map<String, Object> map1 = new HashMap<>();
        map.put("lastname", "last2");
        map.put("firstname", "first2");
        map.put("middlename", "middle2");
        map.put("status", "status1");
        map.put("id", 1);
        map.put("groupId", 2);
        jacksonData = mapper.writeValueAsString(map);
        jacksonData1 = mapper.writeValueAsString(map1);
        Group group = new Group("group", 1);
        student1 = new StudentDB("last1",
                "first1",
                "middle1",
                group.getGroupId(),
                1,
                "status");
        student2 = new StudentDB("last2",
                "first2",
                "middle2",
                group.getGroupId(),
                2,
                "status");
    }

    @Test
    public void addStudentsTest() throws ServerException, AddStudentException, IOException {
        String response = server.execute(jacksonData, "addStudent");
        ResponseEntity<CommonResponse<AddStudentResponse>> result = mapper.readValue(response,
                new TypeReference<ResponseEntity<CommonResponse<AddStudentResponse>>>(){});
        ResponseEntity<CommonResponse<AddStudentResponse>> expected = new ResponseEntity<>(
                new CommonResponse<AddStudentResponse>(new AddStudentResponse(1, student1)),
                201
        );
        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteStudentTest() throws IOException, ServerException {
        String response = server.execute(jacksonData, "deleteStudent");
        DeleteStudentRequest deleteStudentRequest = new DeleteStudentRequest(1);
        String responseDelete = server.execute(mapper.writeValueAsString(deleteStudentRequest),
                "deleteStudent");
        ResponseEntity<CommonResponse<DeleteStudentResponse>> result = mapper.readValue(response,
                new TypeReference<ResponseEntity<CommonResponse<DeleteStudentResponse>>>(){});
        ResponseEntity<CommonResponse<DeleteStudentResponse>> expected = new ResponseEntity<>(
                new CommonResponse<DeleteStudentResponse>(new DeleteStudentResponse(1)),
                201
        );
        System.out.println(responseDelete);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void editStudentTest() throws IOException, ServerException {
        server.execute(jacksonData1, "addStudent");
        String response = server.execute(jacksonData, "editStudent");
        ResponseEntity<CommonResponse<EditStudentResponse>> result = mapper.readValue(response,
                new TypeReference<ResponseEntity<CommonResponse<EditStudentResponse>>>(){});
        ResponseEntity<CommonResponse<EditStudentResponse>> expected = new ResponseEntity<>(
                new CommonResponse<EditStudentResponse>(null),
                201
        );

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getStudentByIdTest() throws IOException, ServerException {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("lastname", "last1");
        map1.put("firstname", "first1");
        map1.put("middlename", "middle1");
        map1.put("status", "status");
        map1.put("id", 1);
        map1.put("groupId", 1);
        String jacksonData = mapper.writeValueAsString(map1);
        Group group = new Group("group", 1);
        Student student11 = new Student("last1",
                "first1",
                "middle1",
                new Group("group", 1),
                1,
                "status");
        Student student21 = new Student("last2",
                "first2",
                "middle2",
                new Group("group", 1),
                2,
                "status");
        String response = server.execute(jacksonData, "getStudentById");
        ResponseEntity<CommonResponse<GetStudentByIdResponse>> result = mapper.readValue(response,
                new TypeReference<ResponseEntity<CommonResponse<GetStudentByIdResponse>>>(){});
        ResponseEntity<CommonResponse<GetStudentByIdResponse>> expected = new ResponseEntity<>(
                new CommonResponse<GetStudentByIdResponse>(new GetStudentByIdResponse(1,
                        student11.getLastname(),
                        student11.getFirstname(),
                        student11.getMiddlename(),
                        student11.getGroup().getName(),
                        student11.getStatus())),
                201
        );
        Assert.assertEquals(expected, result);
    }

}
