package com.haulmont.testtask.Dao;

public abstract class DaoFactory {
    public enum DaoFactoryTypes {HSQLDB}

    public static DaoFactory getDaoFactory(DaoFactoryTypes daoFactoryTypes) {
        switch (daoFactoryTypes) {
            case HSQLDB:
                return new HsqlDbDaoFactory();
            default:
                return null;
        }
    }
}
