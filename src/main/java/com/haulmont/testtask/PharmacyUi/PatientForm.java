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

    private Button btnSave = new Button("Сохранить");
    private Button btnDelete = new Button("Удалить");

    private MainUI mainUi;
    private Binder<Patient> binder = new Binder<>(Patient.class);

    private PatientDao patientDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD).getPatientDao();
    private Patient patient;

    public PatientForm(MainUI mainUi) {
        this.mainUi = mainUi;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(btnSave, btnDelete);
        addComponents(forename, patronymic, surname, phone);

        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binder.bindInstanceFields(this);

        btnSave.addClickListener(ev -> {
            try {
                save();
            } catch (SQLException | ClassNotFoundException exc) {
                exc.printStackTrace();
            }
        });

        btnDelete.addClickListener(ev -> {
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

        btnDelete.setVisible(patient.isPersisted());
        setVisible(true);
        forename.selectAll();
    }

    private void save() throws SQLException, ClassNotFoundException {
        patientDao.insertPatient(patient);
        mainUi.showAllMedicalPrescriptions();
        setVisible(false);
    }

    private void delete() throws SQLException, ClassNotFoundException {
        patientDao.deletePatient(patient.getId());
        mainUi.showAllMedicalPrescriptions();
        setVisible(false);
    }
}
