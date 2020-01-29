package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.Dao.HsqldbDao;

public class PharmacyHsqldbDao extends HsqldbDao {
    public PharmacyHsqldbDao(String dbUrl) {
        super(dbUrl);
    }
}
