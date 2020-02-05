package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.Daos.PharmacyDbDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Patient;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPharmacyDbDao;
import com.haulmont.testtask.PharmacyUi.PatientView;
import com.haulmont.testtask.PharmacyUi.PatientWindow;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.sql.SQLException;
import java.util.List;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";
    public static PharmacyDbDao pharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);

    private PatientDao patientDao = pharmacyDbDao.getPatientDao();

    private TextField filterTf = new TextField();
    private Button clearFilterTfBtn = new Button(VaadinIcons.CLOSE);
    private CssLayout filteringLayout = new CssLayout();

    private Button addPatientBtn = new Button("Добавить пациента");
    private HorizontalLayout toolbarLayout = new HorizontalLayout(filteringLayout, addPatientBtn);

    private Grid<Patient> patientsGrid = new Grid<>(Patient.class);
    private HorizontalLayout gridLayout = new HorizontalLayout();

    private Button updatePatientBtn = new Button("Изменить");
    private Button deletePatientBtn = new Button("Удалить");
    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    private VerticalLayout mainLayout = new VerticalLayout();

    private Patient selectedPatient;

    @Override
    protected void init(VaadinRequest request) {
        setFilterTextField();
        setClearFilterTfBtn();
        filteringLayout.addComponents(filterTf, clearFilterTfBtn);
        filteringLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        setAddPatientBtn();
        toolbarLayout.addComponents(filteringLayout, addPatientBtn);

        setPatientsGrid();
        gridLayout.addComponents(patientsGrid);
        gridLayout.setSizeFull();
        gridLayout.setExpandRatio(patientsGrid, 1);

        setButtons();
        buttonsLayout.addComponents(updatePatientBtn, deletePatientBtn);

        mainLayout.addComponents(toolbarLayout, gridLayout, buttonsLayout);
        updatePatientsGrid();
        setContent(mainLayout);

        // region навигатор
        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button view1 = new Button("View 1", e -> getNavigator().navigateTo("view1"));
        view1.addStyleName(ValoTheme.MENU_ITEM);
        Button view2 = new Button("View 2", e -> getNavigator().navigateTo("view2"));
        view2.addStyleName(ValoTheme.MENU_ITEM);

        CssLayout menu = new CssLayout(title, view1, view2);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        CssLayout viewContainer = new CssLayout();

        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        setContent(mainLayout);

        Navigator navigator = new Navigator(this, this);
        navigator.addView("", new PatientView());
        navigator.addView("view1", new PatientView());
        // endregion
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

            PatientWindow patientWindow = new PatientWindow(this);
            addWindow(patientWindow);
            patientWindow.setVisible(true);

            patientWindow.setPatient(new Patient());
        });
    }

    private void setPatientsGrid() {
        patientsGrid.setColumns(
                PatientDao.SURNAME,
                PatientDao.FORENAME,
                PatientDao.PATRONYMIC,
                PatientDao.PHONE);

        patientsGrid.getColumn(PatientDao.SURNAME).setCaption("Фамилия");
        patientsGrid.getColumn(PatientDao.FORENAME).setCaption("Имя");
        patientsGrid.getColumn(PatientDao.PATRONYMIC).setCaption("Отчество");
        patientsGrid.getColumn(PatientDao.PHONE).setCaption("Телефон");

        patientsGrid.setSizeFull();

        patientsGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                setButtonsInvisible();
            } else {
                updatePatientBtn.setVisible(true);
                deletePatientBtn.setVisible(true);
                selectedPatient = event.getValue();
            }
        });
    }

    private void setButtonsInvisible() {
        patientsGrid.asSingleSelect().clear();
        updatePatientBtn.setVisible(false);
        deletePatientBtn.setVisible(false);
    }

    private void setButtons() {
        setButtonsInvisible();

        updatePatientBtn.addClickListener(event -> {
            PatientWindow patientWindow = new PatientWindow(this);
            addWindow(patientWindow);
            patientWindow.setPatient(selectedPatient);
        });

        deletePatientBtn.addClickListener(event -> {
            try {
                PatientWindow patientWindow = new PatientWindow(this);
                addWindow(patientWindow);
                patientWindow.close();

                patientWindow.setPatient(selectedPatient);
                patientWindow.deletePatientDtoFromDb();
            } catch (SQLException | ClassNotFoundException exc) {
                Notification.show("Невозможно удалить пациента");
                exc.printStackTrace();
            } finally {
                setButtonsInvisible();
                selectedPatient = null;
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