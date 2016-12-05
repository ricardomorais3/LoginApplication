package org.academiadecodigo.loginSceneBuilder.model.dao;

import java.util.List;

/**
 * Created by codecadet on 05/12/16.
 */
public interface Dao<T> {

    void add(T t);

    int count();

    void delete();

    List<T> findAll();


}
