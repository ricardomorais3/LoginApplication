package org.academiadecodigo.loginSceneBuilder.persistence;

import org.hibernate.Session;

/**
 * Created by codecadet on 01/12/16.
 */
public interface TransactionManager {

    void beginTransaction();

    void commit();

    void rollback();

    void close();

}
