package org.academiadecodigo.loginSceneBuilder.model.dao;

import org.academiadecodigo.loginSceneBuilder.model.Role;

/**
 * Created by codecadet on 01/12/16.
 */
public interface RoleDao extends Dao<Role>{

    Role findByDescription(String description);

}
