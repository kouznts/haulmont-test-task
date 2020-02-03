package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Patient;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPharmacyDbDao;
import com.haulmont.testtask.PharmacyUi.PatientForm;
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
    private PatientForm patientForm = new PatientForm(this);

    private Grid<Patient> patientsGrid = new Grid<>(Patient.class);
    private TextField tfFilterText = new TextField();

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();

        tfFilterText.setPlaceholder("Отфильтровать по описанию ...");
        tfFilterText.addValueChangeListener(event -> updatePatientsGrid());
        tfFilterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button btnClearTfFilterText = new Button(VaadinIcons.CLOSE);
        btnClearTfFilterText.setDescription("Очистить фильтр");
        btnClearTfFilterText.addClickListener(event -> tfFilterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(tfFilterText, btnClearTfFilterText);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addPatientBtn = new Button("Добавить пациента");
        addPatientBtn.addClickListener(event -> {
            patientsGrid.asSingleSelect().clear();
            patientForm.setPatient(new Patient());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addPatientBtn);

        patientsGrid.setColumns("surname", "forename", "patronymic", "phone");

        HorizontalLayout mainLayout = new HorizontalLayout(patientsGrid, patientForm);
        mainLayout.setSizeFull();
        patientsGrid.setSizeFull();
        mainLayout.setExpandRatio(patientsGrid, 1);

        layout.addComponents(toolbar, mainLayout);

        updatePatientsGrid();

        setContent(layout);

        patientForm.setVisible(false);

        patientsGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                patientForm.setVisible(false);
            } else {
                patientForm.setPatient((event.getValue()));
            }
        });
    }

    public void updatePatientsGrid() {
        try {
            List<Patient> patients;

            String searchSurname = tfFilterText.getValue();
            if (searchSurname.equals("")) {
                patients = patientDao.getAllPatients();
            } else {
                patients = patientDao.getPatientsBySurname(searchSurname);
            }

            patientsGrid.setItems(patients);
        } catch (SQLException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }
}