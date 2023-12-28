package fctk.server.repository;

import fctk.server.entity.Group;

import java.util.Map;
import java.util.NoSuchElementException;

public class GroupRepository implements GroupRepositoryInterface {
    private Map<Long, Group> groups;
    long id = 0;

    public GroupRepository(DataBase dataBase) {
        this.groups = dataBase.getGroupTable();
    }

    @Override
    public long addGroup(Group group) {
        Group newGroup = new Group(group.getName(), ++id);
        groups.put(id, newGroup);
        return id;
    }

    @Override
    public Group editGroup(Group newGroup, long id) throws NoSuchElementException {
        if (groups.get(id) == null) {
            throw new NoSuchElementException();
        }
        Group new_group = new Group(newGroup.getName(), id);
        groups.put(id, new_group);
        return new_group;
    }

    @Override
    public void deleteGroup(long id) throws NoSuchElementException{
        if(groups.get(id) == null){
            throw new NoSuchElementException();
        } else {
            groups.remove(id);
        }
    }

    @Override
    public Group getGroupById(long id) {
        return null;
    }

    @Override
    public Group[] getGroups() {
        return new Group[0];
    }
}
