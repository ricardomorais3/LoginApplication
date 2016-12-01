package org.academiadecodigo.loginSceneBuilder.persistence;

/**
 * Created by codecadet on 01/12/16.
 */
public class TransactionException extends RuntimeException{

    public TransactionException(Throwable e) {
        super(e);
    }
}
