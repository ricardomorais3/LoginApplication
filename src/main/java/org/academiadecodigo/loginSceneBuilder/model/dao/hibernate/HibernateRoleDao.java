package org.academiadecodigo.loginSceneBuilder.model.dao.hibernate;

import org.academiadecodigo.loginSceneBuilder.model.Role;
import org.academiadecodigo.loginSceneBuilder.model.dao.RoleDao;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateRoleDao extends HibernateDao<Role> implements RoleDao {

    public HibernateRoleDao() {
        super(Role.class);
    }

    @Override
    public Role findByDescription(String description) {
        return null;
    }
}
