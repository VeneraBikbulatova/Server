package fctk.server.services;

import fctk.server.entity.Student;
import fctk.server.entity.StudentDB;
import fctk.server.exceptions.*;
import fctk.server.repository.RepositoryException;
import fctk.server.request.*;
import fctk.server.response.AddStudentResponse;
import fctk.server.response.GetStudentByIdResponse;
import fctk.server.response.GetStudentsByGroupResponse;

import java.util.List;

public interface StudentServiceInterface {
    long addStudent(StudentDB studentDB) throws AddStudentException, ServerException;
    GetStudentByIdResponse getStudentById(long id) throws GetStudentByIdException, RepositoryException, ServerException;
    void deleteStudent(long id) throws DeleteStudentException, RepositoryException, ServerException;
    void editStudent(StudentDB studentDB) throws EditStudentException, ServerException;
    List<Student> getStudentsByGroup(long groupId) throws ServerException;

}
