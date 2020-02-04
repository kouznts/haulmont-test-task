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
    private MainUI mainUi;
    private Binder<Patient> binder = new Binder<>(Patient.class);

    private PatientDao patientDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD).getPatientDao();
    private Patient patient;

    private TextField forename = new TextField("Имя");
    private TextField patronymic = new TextField("Отчество");
    private TextField surname = new TextField("Фамилия");
    private TextField phone = new TextField("Телефон");

    private Button saveBtn = new Button("Сохранить");
    private Button deleteBtn = new Button("Удалить");

    private HorizontalLayout buttons;

    public PatientForm(MainUI mainUi) {
        this.mainUi = mainUi;

        binder.bindInstanceFields(this);

        buttons = new HorizontalLayout(saveBtn, deleteBtn);

        setSizeUndefined();
        addComponents(forename, patronymic, surname, phone, buttons);

        setSaveBtn();
        setDeleteBtn();
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        binder.setBean(patient);

        deleteBtn.setVisible(patient.isPersisted());
        setVisible(true);
        forename.selectAll();
    }

    private void setSaveBtn() {
        saveBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        saveBtn.addClickListener(event -> {
            try {
                savePatientDtoIntoDb();
            } catch (SQLException | ClassNotFoundException exc) {
                exc.printStackTrace();
            }
        });
    }

    private void setDeleteBtn() {
        deleteBtn.addClickListener(event -> {
            try {
                deletePatientDtoFromDb();
            } catch (SQLException | ClassNotFoundException exc) {
                exc.printStackTrace();
            }
        });
    }

    private void savePatientDtoIntoDb() throws SQLException, ClassNotFoundException {
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

    private void deletePatientDtoFromDb() throws SQLException, ClassNotFoundException {
        patientDao.deletePatient(patient.getId());
        mainUi.updatePatientsGrid();
        setVisible(false);
    }
}
