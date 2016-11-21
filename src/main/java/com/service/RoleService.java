package com.service;

import com.exeptions.DuplicateRoleException;
import com.exeptions.RoleNotFoundException;
import com.model.Role;

import java.util.List;

public interface RoleService {

    public void addRole(Role role) throws DuplicateRoleException;

    public Role getRole(int id) throws RoleNotFoundException;

    public Role getRole(String rolename) throws RoleNotFoundException;

    public void updateRole(Role role) throws RoleNotFoundException;

    public void deleteRole(int id) throws RoleNotFoundException;

    public List<Role> getRoles();
}
