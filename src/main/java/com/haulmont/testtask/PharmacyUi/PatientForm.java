package com.haulmont.testtask.PharmacyUi;

import com.haulmont.testtask.MainUI;
import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Patient;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPharmacyDbDao;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.sql.SQLException;

import static com.haulmont.testtask.MainUI.*;

public class PatientForm extends FormLayout {
    private TextField forename = new TextField("Имя");
    private TextField patronymic = new TextField("Отчество");
    private TextField surname = new TextField("Фамилия");
    private TextField phone = new TextField("Телефон");

    private Button saveBtn = new Button("Сохранить");
    private Button deleteBtn = new Button("Удалить");

    private MainUI mainUi;
    private Binder<Patient> binder = new Binder<>(Patient.class);

    private PatientDao patientDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD).getPatientDao();
    private Patient patient;

    public PatientForm(MainUI mainUi) {
        this.mainUi = mainUi;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(saveBtn, deleteBtn);
        addComponents(forename, patronymic, surname, phone, buttons);

        saveBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binder.bindInstanceFields(this);

        saveBtn.addClickListener(event -> {
            try {
                save();
            } catch (SQLException | ClassNotFoundException exc) {
                exc.printStackTrace();
            }
        });

        deleteBtn.addClickListener(event -> {
            try {
                delete();
            } catch (SQLException | ClassNotFoundException exc) {
                exc.printStackTrace();
            }
        });
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        binder.setBean(patient);

        deleteBtn.setVisible(patient.isPersisted());
        setVisible(true);
        forename.selectAll();
    }

    private void save() throws SQLException, ClassNotFoundException {
        updatePatientDto();

        if (patient.isPersisted()) {
            patientDao.updatePatient(patient);
        } else {
            patientDao.insertPatient(patient);
        }

        mainUi.updatePatientsGrid();
        setVisible(false);
    }

    private void updatePatientDto() {
        patient.setForename(forename.getValue());
        patient.setPatronymic(patronymic.getValue());
        patient.setSurname(surname.getValue());
        patient.setPhone(phone.getValue());
    }

    private void delete() throws SQLException, ClassNotFoundException {
        patientDao.deletePatient(patient.getId());
        mainUi.updatePatientsGrid();
        setVisible(false);
    }
}
