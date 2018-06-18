/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.controller;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import pl.polsl.musiol.kryspin.ejb.ModelFacadeBeanLocal;
import pl.polsl.musiol.kryspin.ejb.PainterServiceBeanLocal;
import pl.polsl.musiol.kryspin.model.Painter;
import pl.polsl.musiol.kryspin.model.PainterHolder;

/**
 * Converter to transform Painter object to string and vice versa
 * 
 * @author Kryspin Musiol
 * @version 154.23
 */
@FacesConverter(value = "painterConverter")
//@ManagedBean
//@RequestScoped
public class PainterConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(PainterController.class.getName());

//    @EJB
    private PainterServiceBeanLocal psbl = lookup();

    private PainterServiceBeanLocal lookup() {
        try {
            return (PainterServiceBeanLocal) new InitialContext().lookup("java:global/Lab4-EAR/Lab4-EAR-ejb/PainterServiceBean");
        } catch (NamingException ex) {
            Logger.getLogger(PainterConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        try {
            if (value == null || value.isEmpty()) {
                return null;
            }

            if (!value.matches("\\d+")) {
                throw new ConverterException("The value is not a valid ID number: " + value);
            }

            Integer id = Integer.valueOf(value);
            Painter p = psbl.findPainter(id);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Painter %s %d :(", value, e.toString(), Long.valueOf((value)))), e);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        try {
            if (value != null) {
                if (value instanceof Painter) {
                    Painter p = (Painter) value;
                    return getStringKey(p.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Painter", value)), e);
        }

        return value != null ? value.toString() : "";
    }

    private String getStringKey(Integer id) {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        return sb.toString();
    }
}
