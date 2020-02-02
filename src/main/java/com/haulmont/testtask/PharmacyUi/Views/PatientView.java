package com.haulmont.testtask.PharmacyUi.Views;

import com.haulmont.testtask.PharmacyUi.Designs.PatientDesign;
import com.vaadin.ui.Button;

public class PatientView extends PatientDesign {
    public PatientView() {
        btnAdd.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                btnAdd.setCaption("You made me click!");
            }
        });
    }
}
