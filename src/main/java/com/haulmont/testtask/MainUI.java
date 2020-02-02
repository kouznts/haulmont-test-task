package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Patient;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPharmacyDbDao;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.sql.SQLException;
import java.util.List;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    private HsqldbPharmacyDbDao hsqldbPharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);
    private PatientDao patientDao = hsqldbPharmacyDbDao.getPatientDao();

    private Grid<Patient> gridPatients = new Grid<>(Patient.class);

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        layout.addComponents(gridPatients);

        showAllPatients();

        setContent(layout);
    }

    public void showAllPatients() {
        try {
            List<Patient> patients = patientDao.getAllPatients();
            gridPatients.setColumns("surname", "forename", "patronymic", "phone", "id");
            gridPatients.setItems(patients);
        } catch (SQLException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }
}