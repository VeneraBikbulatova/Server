package fctk.server.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.Server;
import fctk.server.entity.Group;
import fctk.server.entity.Student;
import fctk.server.entity.StudentDB;
import fctk.server.exceptions.AddStudentException;
import fctk.server.exceptions.ServerException;
import fctk.server.repository.DataBase;
import fctk.server.request.DeleteStudentRequest;
import fctk.server.request.GetStudentsByGroupRequest;
import fctk.server.response.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentsTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    static final DataBase db = new DataBase();
    static Server server;
    static String jacksonData;
    static String jacksonData1;
    static Student student1;
    static Student student2;
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
        ObjectMapper mapper1 = new ObjectMapper();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("lastname", "last2");
        map1.put("firstname", "first2");
        map1.put("middlename", "middle2");
        map1.put("status", "status1");
        map1.put("id", 1);
        map1.put("groupId", 2);
        jacksonData = mapper.writeValueAsString(map);
        jacksonData1 = mapper.writeValueAsString(map1);
        Group group = new Group("group", 1);
        student1 = new Student("last1",
                "first1",
                "middle1",
                group,
                1,
                "status");
        student2 = new Student("last2",
                "first2",
                "middle2",
                group,
                2,
                "status");
    }

    @Test
    public void addStudentsTest() throws ServerException, AddStudentException, IOException {
        String response = server.execute(jacksonData, "addStudent");
        ResponseEntity<CommonResponse<AddStudentResponse>> result = mapper.readValue(response,
                new TypeReference<ResponseEntity<CommonResponse<AddStudentResponse>>>(){});
        ResponseEntity<CommonResponse<AddStudentResponse>> expected = new ResponseEntity<>(
                new CommonResponse<AddStudentResponse>(new AddStudentResponse(1, new StudentDB(
                        student1.getLastname(),
                        student1.getFirstname(),
                        student1.getMiddlename(),
                        student1.getId(),
                        student1.getGroup().getGroupId(),
                        student1.getStatus()
                ))),
                201
        );
        Assert.assertEquals(expected, result);
    }

    @Test
    public void editAndGetStudentTest() throws IOException, ServerException {
        server.execute(jacksonData1, "addStudent");
        String response = server.execute(jacksonData, "editStudent");
        ResponseEntity<CommonResponse<EditStudentResponse>> result = mapper.readValue(response,
                new TypeReference<ResponseEntity<CommonResponse<EditStudentResponse>>>(){});
        ResponseEntity<CommonResponse<EditStudentResponse>> expected = new ResponseEntity<>(
                new CommonResponse<EditStudentResponse>(null),
                201
        );
        Assert.assertEquals(expected, result);
        Map<String, Object> m = new HashMap<>();
        m.put("id", 1);
        String getJacksonData = mapper.writeValueAsString(m);
        String getS = server.execute(getJacksonData, "getStudentById");
        ResponseEntity<CommonResponse<GetStudentByIdResponse>> getStudent = mapper.readValue(getS,
                new TypeReference<ResponseEntity<CommonResponse<GetStudentByIdResponse>>>(){});
        ResponseEntity<CommonResponse<GetStudentByIdResponse>> getExpected = new ResponseEntity<>(
                new CommonResponse<GetStudentByIdResponse>(new GetStudentByIdResponse(1,
                        student1.getLastname(),
                        student1.getFirstname(),
                        student1.getMiddlename(),
                        student1.getGroup().getName(),
                        student1.getStatus())),
                201
        );

        Assert.assertEquals(getExpected, getStudent);
    }

    @Test
    public void deleteStudentTest() throws IOException, ServerException {
        server.execute(jacksonData, "addStudent");
        Map<String, Object> m = new HashMap<>();
        m.put("id", 1);
        String deleteJacksonData = mapper.writeValueAsString(m);
        String response = server.execute(deleteJacksonData, "deleteStudent");
        DeleteStudentRequest deleteStudentRequest = new DeleteStudentRequest(1);
        server.execute(mapper.writeValueAsString(deleteStudentRequest),
                "deleteStudent");
        ResponseEntity<CommonResponse<DeleteStudentResponse>> result = mapper.readValue(response,
                new TypeReference<ResponseEntity<CommonResponse<DeleteStudentResponse>>>(){});
        ResponseEntity<CommonResponse<DeleteStudentResponse>> expected = new ResponseEntity<>(
                new CommonResponse<>(null),
                201
        );
        Assert.assertEquals(expected, result);
    }

}
