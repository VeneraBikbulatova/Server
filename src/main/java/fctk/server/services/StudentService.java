package fctk.server.services;
import fctk.server.entity.Group;
import fctk.server.entity.Student;
import fctk.server.entity.StudentDB;
import fctk.server.exceptions.*;
import fctk.server.repository.RepositoryException;
import fctk.server.repository.StudentRepository;
import fctk.server.request.*;
import fctk.server.response.AddStudentResponse;
import fctk.server.response.GetStudentByIdResponse;
import fctk.server.response.GetStudentsByGroupResponse;

import java.util.List;

public class StudentService implements StudentServiceInterface{
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
         this.studentRepository = studentRepository;
    }

    @Override
    public long addStudent(StudentDB studentDB) throws AddStudentException, ServerException {
        try {
            System.out.println(studentRepository.addStudent(studentDB));
            return studentRepository.addStudent(studentDB);
        } catch (Exception exception) {
            throw new ServerException("somthng goes wrong");
        }
    }

    @Override
    public Student getStudentById(long id) throws GetStudentByIdException, RepositoryException, ServerException {
        try {
            return studentRepository.getStudentById(id);
        } catch (Exception exception) {
            throw new ServerException("smth goes wrong");
        }
    }

    @Override
    public void deleteStudent(long id) throws DeleteStudentException, RepositoryException, ServerException {
        try {
            studentRepository.deleteStudent(id);
        } catch (Exception exception) {
            throw new ServerException("smth goes wrong");
        }
    }

    @Override
    public void editStudent(StudentDB studentDB) throws EditStudentException, ServerException {
        try{
            studentRepository.editStudent(studentDB);
        } catch (Exception e) {
            throw new ServerException("smth goes wrong");
        }
    }

    @Override
    public List<Student> getStudentsByGroup(long groupId) throws ServerException {
        try {
            return studentRepository.getStudentsByGroup(groupId);
        } catch (Exception e) {
            throw new ServerException("smth goes wrong");
        }
    }
}
