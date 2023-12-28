package fctk.server.repository;

import fctk.server.entity.Student;
import fctk.server.entity.StudentDB;

import java.util.List;

public interface StudentRepositoryInterface {
    long addStudent(StudentDB student) throws RepositoryException;

    Student editStudent(StudentDB student) throws RepositoryException;

    void deleteStudent(long id) throws RepositoryException;

    Student getStudentById(long id) throws RepositoryException;

    List<Student> getStudentsByGroup(long group) throws RepositoryException;
}
