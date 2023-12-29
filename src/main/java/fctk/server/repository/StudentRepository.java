package fctk.server.repository;

import fctk.server.entity.Group;
import fctk.server.entity.Student;
import fctk.server.entity.StudentDB;

import java.util.*;
import java.util.stream.Collectors;

public class StudentRepository implements StudentRepositoryInterface{
    private DataBase dataBase;

    public StudentRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public long addStudent(StudentDB student) {
        StudentDB newStudent = new StudentDB(
                student.getLastname(),
                student.getFirstname(),
                student.getMiddlename(),
                student.getGroupId(),
                dataBase.students.keySet().stream()
                        .max(Comparator.naturalOrder())
                        .orElse(0L)+1,
                student.getStatus());
        dataBase.getStudents().put(newStudent.getId(), newStudent);
        return newStudent.getGroupId();
    }

    @Override
    public Student editStudent(StudentDB student) throws RepositoryException {
        if(dataBase.getStudents().get(student.getId()) == null){
            throw new RepositoryException("Student with id:" + student.getId() + "not found");
        }
        Student newStudent = new Student(
                student.getLastname(),
                student.getFirstname(),
                student.getMiddlename(),
                dataBase.getGroupTable().get(student.getGroupId()),
                student.getId(),
                student.getStatus());
        dataBase.getStudents().put(student.getId(), student);
        return newStudent;
    }

    @Override
    public void deleteStudent(long id) throws RepositoryException{
        if(dataBase.getStudents().get(id) == null){
            throw new RepositoryException("Student with id:" + id + "not found");
        }
        dataBase.getStudents().remove(id);
    }

    @Override
    public Student getStudentById(long id) throws RepositoryException{
        if(dataBase.getStudents().get(id) == null){
            throw new RepositoryException("Student with id:" + id + "not found");
        }
        StudentDB currentDBStudent = dataBase.getStudents().get(id);
        Student student = new Student(
                currentDBStudent.getLastname(),
                currentDBStudent.getFirstname(),
                currentDBStudent.getMiddlename(),
                Optional.ofNullable(
                    dataBase.getGroupTable().get(currentDBStudent.getGroupId())
                ).orElseThrow(() -> new RepositoryException("Group not found")),
                currentDBStudent.getId(),
                currentDBStudent.getStatus()
        );
        return student;
    }

    @Override
    public List<Student> getStudentsByGroup(long groupId) throws RepositoryException{
        Map<Long, Group> groups = dataBase.getGroupTable();
        Group currentGroup = Optional.ofNullable(
                dataBase.getGroupTable().get(groupId)
        ).orElseThrow(() -> new RepositoryException("Group not found"));
        return dataBase.getStudents().entrySet().stream()
                .filter(entry -> entry.getValue().getGroupId() == groupId)
                .map(entry -> new Student(
                        entry.getValue().getLastname(),
                        entry.getValue().getFirstname(),
                        entry.getValue().getMiddlename(),
                        currentGroup,
                        entry.getValue().getId(),
                        entry.getValue().getStatus()
                        ))
                .collect(Collectors.toList());
    }
}
