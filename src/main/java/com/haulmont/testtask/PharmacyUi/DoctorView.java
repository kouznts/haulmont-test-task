package com.haulmont.testtask.PharmacyUi;

import com.haulmont.testtask.MainUI;
import com.haulmont.testtask.PharmacyDb.Daos.DoctorDao;
import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Doctor;
import com.haulmont.testtask.PharmacyDb.Dtos.Patient;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.sql.SQLException;
import java.util.List;

import static com.haulmont.testtask.MainUI.pharmacyDbDao;

public class DoctorView extends VerticalLayout implements View {
    private MainUI mainUi;

    private DoctorDao doctorDao;

    private TextField filterTf;
    private Button clearFilterTfBtn;
    private CssLayout filteringLayout;

    private Button addDoctorBtn;
    private HorizontalLayout toolbarLayout;

    private Grid<Doctor> doctorsGrid;
    private HorizontalLayout gridLayout;

    private Button updateDoctorBtn;
    private Button deleteDoctorBtn;
    private HorizontalLayout buttonsLayout;

    private Doctor selectedDoctor;

    public DoctorView(MainUI mainUi) {
        // region поля
        this.mainUi = mainUi;

        doctorDao = pharmacyDbDao.getDoctorDao();

        filterTf = new TextField();
        clearFilterTfBtn = new Button(VaadinIcons.CLOSE);
        filteringLayout = new CssLayout(filterTf, clearFilterTfBtn);

        addDoctorBtn = new Button("Добавить доктора");
        toolbarLayout = new HorizontalLayout(filteringLayout, addDoctorBtn);

        doctorsGrid = new Grid<>(Doctor.class);
        gridLayout = new HorizontalLayout(doctorsGrid);

        updateDoctorBtn = new Button("Изменить");
        deleteDoctorBtn = new Button("Удалить");
        buttonsLayout = new HorizontalLayout(updateDoctorBtn, deleteDoctorBtn);
        // endregion

        setFilterTextField();
        setClearFilterTfBtn();
        filteringLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        setAddPatientBtn();

        setPatientsGrid();
        gridLayout.setSizeFull();
        gridLayout.setExpandRatio(doctorsGrid, 1);

        setButtons();

        addComponents(toolbarLayout, gridLayout, buttonsLayout);
        updatePatientsGrid();
    }

    private void setFilterTextField() {
        filterTf.setPlaceholder("Поиск по фамилии...");
        filterTf.addValueChangeListener(event -> updatePatientsGrid());
        filterTf.setValueChangeMode(ValueChangeMode.LAZY);
    }

    private void setClearFilterTfBtn() {
        clearFilterTfBtn.setDescription("Очистить фильтр");
        clearFilterTfBtn.addClickListener(event -> filterTf.clear());
    }

    private void setAddPatientBtn() {
        addDoctorBtn.addClickListener(event -> {
            doctorsGrid.asSingleSelect().clear();

            PatientWindow patientWindow = new PatientWindow(mainUi, this);
            mainUi.addWindow(patientWindow);
            patientWindow.setVisible(true);

            patientWindow.setPatient(new Patient());
        });
    }

    private void setPatientsGrid() {
        doctorsGrid.setColumns(
                PatientDao.SURNAME,
                PatientDao.FORENAME,
                PatientDao.PATRONYMIC,
                PatientDao.PHONE);

        doctorsGrid.getColumn(PatientDao.SURNAME).setCaption("Фамилия");
        doctorsGrid.getColumn(PatientDao.FORENAME).setCaption("Имя");
        doctorsGrid.getColumn(PatientDao.PATRONYMIC).setCaption("Отчество");
        doctorsGrid.getColumn(PatientDao.PHONE).setCaption("Телефон");

        doctorsGrid.setSizeFull();

        doctorsGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                setButtonsInvisible();
            } else {
                updateDoctorBtn.setVisible(true);
                deleteDoctorBtn.setVisible(true);
                selectedDoctor = event.getValue();
            }
        });
    }

    private void setButtonsInvisible() {
        doctorsGrid.asSingleSelect().clear();
        updateDoctorBtn.setVisible(false);
        deleteDoctorBtn.setVisible(false);
    }

    private void setButtons() {
        setButtonsInvisible();

        updateDoctorBtn.addClickListener(event -> {
            PatientWindow patientWindow = new PatientWindow(mainUi, this);
            mainUi.addWindow(patientWindow);
            patientWindow.setPatient(selectedDoctor);
        });

        deleteDoctorBtn.addClickListener(event -> {
            try {
                PatientWindow patientWindow = new PatientWindow(mainUi, this);
                mainUi.addWindow(patientWindow);
                patientWindow.close();

                patientWindow.setPatient(selectedDoctor);
                patientWindow.deletePatientDtoFromDb();
            } catch (SQLException | ClassNotFoundException exc) {
                Notification.show("Невозможно удалить пациента");
                exc.printStackTrace();
            } finally {
                setButtonsInvisible();
                selectedDoctor = null;
            }
        });
    }

    public void updatePatientsGrid() {
        try {
            List<Patient> patients;

            String searchSurname = filterTf.getValue();
            if (searchSurname.equals("")) {
                patients = doctorDao.getAllPatients();
            } else {
                patients = doctorDao.getPatientsBySurname(searchSurname);
            }

            doctorsGrid.setItems(patients);
        } catch (SQLException | ClassNotFoundException exc) {
            Notification.show(exc.getMessage());
            exc.printStackTrace();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }
}
