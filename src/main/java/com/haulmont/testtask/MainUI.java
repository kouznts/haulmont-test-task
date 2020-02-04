package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Patient;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPharmacyDbDao;
import com.haulmont.testtask.PharmacyUi.PatientWindow;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.sql.SQLException;
import java.util.List;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    public static final String DB_URL = "jdbc:hsqldb:file:testdb";
    public static final String USER = "SA";
    public static final String PASSWORD = "";

    private HsqldbPharmacyDbDao hsqldbPharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);

    private PatientDao patientDao = hsqldbPharmacyDbDao.getPatientDao();
    private PatientWindow patientWindow = new PatientWindow(this);

    private TextField filterTf = new TextField();
    private Button clearFilterTfBtn = new Button(VaadinIcons.CLOSE);
    private CssLayout filteringLayout = new CssLayout();

    private Button addPatientBtn = new Button("Добавить пациента");
    private HorizontalLayout toolbarLayout = new HorizontalLayout(filteringLayout, addPatientBtn);

    private Grid<Patient> patientsGrid = new Grid<>(Patient.class);
    private HorizontalLayout horizontalLayout = new HorizontalLayout();

    private VerticalLayout mainLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest request) {
        setFilterTextField();
        setClearFilterTfBtn();
        filteringLayout.addComponents(filterTf, clearFilterTfBtn);
        filteringLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        setAddPatientBtn();
        toolbarLayout.addComponents(filteringLayout, addPatientBtn);

        setPatientsGrid();
        horizontalLayout.addComponents(patientsGrid);
        addWindow(patientWindow);
        horizontalLayout.setSizeFull();
        horizontalLayout.setExpandRatio(patientsGrid, 1);

        mainLayout.addComponents(toolbarLayout, horizontalLayout);
        updatePatientsGrid();
        setContent(mainLayout);
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
        addPatientBtn.addClickListener(event -> {
            patientsGrid.asSingleSelect().clear();
            patientWindow.setPatient(new Patient());
        });
    }

    private void setPatientsGrid() {
        patientsGrid.setColumns("surname", "forename", "patronymic", "phone");
        patientsGrid.setSizeFull();
        patientWindow.setVisible(false);

        patientsGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                patientWindow.setVisible(false);
            } else {
                patientWindow.setPatient((event.getValue()));
            }
        });
    }

    public void updatePatientsGrid() {
        try {
            List<Patient> patients;

            String searchSurname = filterTf.getValue();
            if (searchSurname.equals("")) {
                patients = patientDao.getAllPatients();
            } else {
                patients = patientDao.getPatientsBySurname(searchSurname);
            }

            patientsGrid.setItems(patients);
        } catch (SQLException | ClassNotFoundException exc) {
            Notification.show(exc.getMessage());
            exc.printStackTrace();
        }
    }
}