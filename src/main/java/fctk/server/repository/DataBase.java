package fctk.server.repository;

import fctk.server.entity.Group;
import fctk.server.entity.Student;
import fctk.server.entity.StudentDB;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    protected Map<Long, Group> groupTable;
    protected Map<Long, StudentDB> students;

    public DataBase(){
        groupTable = new HashMap<>();
        students = new HashMap<>();
    }

    public Map<Long, Group> getGroupTable() {
        return groupTable;
    }

    public Map<Long, StudentDB> getStudents() {
        return students;
    }

}
