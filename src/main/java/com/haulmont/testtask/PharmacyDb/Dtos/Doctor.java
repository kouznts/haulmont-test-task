package com.haulmont.testtask.PharmacyDb.Dtos;

public class Doctor {
    private long id;
    private String forename;
    private String patronymic;
    private String surname;
    private long specializationId;

    public Doctor(String forename, String patronymic, String surname, long specializationId) {
        this.id = -1;
        this.forename = forename;
        this.patronymic = patronymic;
        this.surname = surname;
        this.specializationId = specializationId;
    }

    public Doctor(long id, String forename, String patronymic, String surname, long specializationId) {
        this.id = id;
        this.forename = forename;
        this.patronymic = patronymic;
        this.surname = surname;
        this.specializationId = specializationId;
    }

    public long getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public long getSpecializationId() {
        return specializationId;
    }

    @Override
    public String toString() {
        return Long.toString(id) + '\n' +
                forename + '\n' +
                patronymic + '\n' +
                surname + '\n' +
                Long.toString(specializationId);
    }
}
