/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.model;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * Listener for simple validation of the provided name
 * 
 * @author krysp
 * @version 1.0
 * 
 * 
 */
public class NameValidationListener {

    /**
     * Performs the validation
     * @param painter 
     */
    @PrePersist
    @PreUpdate
    private void validate(Painter painter) {
        System.out.println("DataValidationListener validateData()");
        if (painter.getFirstName() == null ||  painter.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Invalid first name.");
        }
        if (painter.getLastName() == null || painter.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Invalid last name.");
        }
    }
}
