package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.Daos.PharmacyDbDao;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPharmacyDbDao;
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
    private static final String PATIENT_VIEW = "PATIENT VIEW";
    private static final String DOCTOR_VIEW = "DOCTOR VIEW";

    private Label title;
    private Button patientView;
    private Button doctorView;
    private CssLayout menu;
    private CssLayout viewContainer;

    @Override
    protected void init(VaadinRequest request) {
        title = new Label("Меню");
        title.addStyleName(ValoTheme.MENU_TITLE);

        patientView = new Button(PATIENT_VIEW, event -> getNavigator().navigateTo(PATIENT_VIEW));
        patientView.addStyleName(ValoTheme.MENU_ITEM);

        doctorView = new Button(DOCTOR_VIEW, event -> getNavigator().navigateTo(DOCTOR_VIEW));
        doctorView.addStyleName(ValoTheme.MENU_ITEM);

        menu = new CssLayout(title, patientView, doctorView);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        viewContainer = new CssLayout();

        mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        viewContainer.setSizeFull();
        setContent(mainLayout);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", new PatientView(this));
        navigator.addView(PATIENT_VIEW, new PatientView(this));
    }
}