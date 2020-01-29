package com.haulmont.testtask.PharmacyDb.Dtos;

public class DtoPatient {
    private long id;
    private String forename;
    private String patronymic;
    private String surname;
    private String phone;

    public DtoPatient(long id, String forename, String patronymic, String surname, String phone) {
        this.forename = forename;
        this.patronymic = patronymic;
        this.surname = surname;
        this.phone = phone;
    }
}
