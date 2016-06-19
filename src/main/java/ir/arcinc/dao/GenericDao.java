package ir.arcinc.dao;

import ir.arcinc.Connection;

/**
 * Created by tahae on 6/19/2016.
 */
public abstract class GenericDao {
    protected Connection connection;

    public GenericDao(Connection connection) {
        this.connection = connection;
    }


}
