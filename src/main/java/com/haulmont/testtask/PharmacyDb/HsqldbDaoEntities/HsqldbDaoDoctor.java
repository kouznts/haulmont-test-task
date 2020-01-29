package com.haulmont.testtask.PharmacyDb.HsqldbDaoEntities;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableDoctor;
import com.haulmont.testtask.PharmacyDb.DtoEntities.DtoDoctor;

import java.sql.SQLException;
import java.util.List;

public class HsqldbDaoDoctor extends HsqldbDao implements DaoableDoctor {
    public HsqldbDaoDoctor(String dbUrl, String user, String password) {
        super(dbUrl, user, password);
    }

    @Override
    public DtoDoctor findDoctor(long id) throws SQLException, ClassNotFoundException {
        connect();

        disconnect();
        
        return null;
    }

    @Override
    public long insertDoctor(DtoDoctor doctor) {
        return 0;
    }

    @Override
    public boolean updateDoctor(DtoDoctor doctor) {
        return false;
    }

    @Override
    public boolean deleteDoctor(long id) {
        return false;
    }

    @Override
    public List<DtoDoctor> getAllDoctors() {
        return null;
    }
}
