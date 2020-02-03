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

    private Grid<Patient> gridPatients = new Grid<>(Patient.class);
    private TextField tfFilterText = new TextField();

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();

        tfFilterText.setPlaceholder("Отфильтровать по описанию ...");
        tfFilterText.addValueChangeListener(ev -> showAllPatients());
        tfFilterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button btnClearTfFilterText = new Button(VaadinIcons.CLOSE);
        btnClearTfFilterText.setDescription("Очистить фильтр");
        btnClearTfFilterText.addClickListener(ev -> tfFilterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(tfFilterText, btnClearTfFilterText);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        //gridPrescriptions.setColumns("surname", "forename", "patronymic", "phone", "id");

        HorizontalLayout mainLayout = new HorizontalLayout(gridPatients, patientForm);
        mainLayout.setSizeFull();
        gridPatients.setSizeFull();
        mainLayout.setExpandRatio(gridPatients, 1);

        layout.addComponents(filtering, mainLayout);

        showAllPatients();

        setContent(layout);
    }

    public void showAllPatients() {
        try {
            List<Patient> patients;

            String searchSurname = tfFilterText.getValue();
            if (searchSurname.equals(""))
                patients = patientDao.getAllPatients();
            else
                patients = patientDao.getAllPatientsBySurname(searchSurname);

            gridPatients.setItems(patients);
        } catch (SQLException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }
}