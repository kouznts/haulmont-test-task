package com.haulmont.testtask.Pharmacy.Db.Dtos;

public class Patient {
    private long id;
    private String forename;
    private String patronymic;
    private String surname;
    private String phone;

    public Patient(String forename, String patronymic, String surname, String phone) {
        this.id = -1;
        this.forename = forename;
        this.patronymic = patronymic;
        this.surname = surname;
        this.phone = phone;
    }

    public Patient(long id, String forename, String patronymic, String surname, String phone) {
        this.id = id;
        this.forename = forename;
        this.patronymic = patronymic;
        this.surname = surname;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return Long.toString(id) + '\n' +
                forename + '\n' +
                patronymic + '\n' +
                surname + '\n' +
                phone;
    }
}
