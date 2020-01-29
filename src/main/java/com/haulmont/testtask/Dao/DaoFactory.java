package com.haulmont.testtask.Dao;

public abstract class DaoFactory {
    public enum DaoTypes {HSQLDB}

    public static HsqldbDao getDao(DaoTypes daoType, String connectionUrl) {
        switch (daoType) {
            case HSQLDB:
                return new HsqldbDao(connectionUrl);
            default:
                return null;
        }
    }
}
