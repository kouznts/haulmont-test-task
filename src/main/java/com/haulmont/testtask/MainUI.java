package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.Daos.PharmacyDbDao;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPharmacyDbDao;
import com.haulmont.testtask.PharmacyUi.DefaultView;
import com.haulmont.testtask.PharmacyUi.DoctorSpecializationView;
import com.haulmont.testtask.PharmacyUi.DoctorView;
import com.haulmont.testtask.PharmacyUi.PatientView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";
    public static PharmacyDbDao pharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);

    private HorizontalLayout mainLayout;
    private static final String PATIENT_VIEW = "Пациенты";
    private static final String DOCTOR_VIEW = "Врачи";
    private static final String DOCTOR_SPECIALIZATIONS_VIEW = "Специализации";

    private Label title;
    private Button patientViewBtn;
    private Button doctorViewBtn;
    private Button doctorSpecializationViewBtn;
    private CssLayout menu;
    private CssLayout viewContainer;

    @Override
    protected void init(VaadinRequest request) {
        title = new Label("Меню");
        title.addStyleName(ValoTheme.MENU_TITLE);

        patientViewBtn = new Button(PATIENT_VIEW, event -> getNavigator().navigateTo(PATIENT_VIEW));
        patientViewBtn.addStyleName(ValoTheme.MENU_ITEM);

        doctorViewBtn = new Button(DOCTOR_VIEW, event -> getNavigator().navigateTo(DOCTOR_VIEW));
        doctorViewBtn.addStyleName(ValoTheme.MENU_ITEM);

        doctorSpecializationViewBtn = new Button(DOCTOR_SPECIALIZATIONS_VIEW, event -> getNavigator().navigateTo(DOCTOR_SPECIALIZATIONS_VIEW));
        doctorSpecializationViewBtn.addStyleName(ValoTheme.MENU_ITEM);

        menu = new CssLayout(title, patientViewBtn, doctorViewBtn, doctorSpecializationViewBtn);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        viewContainer = new CssLayout();

        mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        viewContainer.setSizeFull();
        setContent(mainLayout);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", new DefaultView());
        navigator.addView(PATIENT_VIEW, new PatientView(this));
        navigator.addView(DOCTOR_VIEW, new DoctorView(this));
        navigator.addView(DOCTOR_SPECIALIZATIONS_VIEW, new DoctorSpecializationView(this));
    }
}