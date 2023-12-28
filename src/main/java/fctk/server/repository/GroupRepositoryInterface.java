package fctk.server.repository;

import fctk.server.entity.Group;

public interface GroupRepositoryInterface {
    long addGroup(Group group) throws RepositoryException;

    Group editGroup(Group group, long id) throws RepositoryException;

    void deleteGroup(long id) throws RepositoryException;

    Group getGroupById(long id) throws RepositoryException;

    Group[] getGroups() throws RepositoryException;
}
