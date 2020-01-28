package com.haulmont.testtask.PharmacyDb.DtoEntities;

public class DtoDoctor {
    private long id;
    private String forename;
    private String patronymic;
    private String surname;
    private long specializationId;

    public DtoDoctor(long id, String forename, String patronymic, String surname, long specializationId) {
        this.forename = forename;
        this.patronymic = patronymic;
        this.surname = surname;
        this.specializationId = specializationId;
    }
}
