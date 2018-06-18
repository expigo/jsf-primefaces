/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import pl.polsl.musiol.kryspin.ejb.ModelFacadeBeanLocal;
import pl.polsl.musiol.kryspin.model.Painter;
import pl.polsl.musiol.kryspin.model.Painting;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Kryspin Musiol
 * @version 38.46
 */
@ManagedBean
@SessionScoped
public class PainterController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PainterController.class.getName());

    /**
     * Stores a list of all painters in the DB.
     */
    private List<Painter> painters;
    /**
     * Stores a list of all paintings in the DB
     */
    private List<Painting> paintings;
    
    /**
     * Object to work on
     */
    private Painter painter2;
    /**
     * The object used later for updating the chosen Painter entity.
     */
    Painter thePainter;

    
    /**
     * Injected EJB module
     */
    @EJB
    private ModelFacadeBeanLocal model;

    
    /**
     * Funciton called after every creation of PainterController's instance
     * Mainly updates the members of the class with values taken from the DB
     */
    @PostConstruct
    protected void init() {

        painter2 = new Painter();
        thePainter = new Painter();
        painters = model.getAllPainters();
        paintings = model.getAllPaintings();
    }

    public List<Painter> getPainters() {
        return painters;
    }

    public void setPainters(List<Painter> painters) {
        this.painters = painters;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
    }


    public Painter getThePainter() {
        return thePainter;
    }

    public void setThePainter(Painter thePainter) {
        this.thePainter = thePainter;
    }

    public Painter getPainter2() {
        return painter2;
    }

    public void setPainter2(Painter painter2) {
        this.painter2 = painter2;
    }
    
    /**
     * Function called in order to fill the list of the painters
     */
    public void loadPainters() {

        painters.clear();
        painters = model.getAllPainters();

        //return "list-painters?faces-redirect=true";
    }

    /**
     * Called when a new entity is going to be added in the DB
     * @return redirects to 'index.xhtml'
     */
    public String addPainter() {

            model.createPainter(painter2);
//            painter2 = new Painter();
            return "index";


    }

    /**
     * Deletes a painter
     * @param painter Painter to be deleted
     */
    public void deletePainter(Painter painter) {

            model.deletePainter(painter.getId());
//            return "index";


    }

    /**
     * Function called to pre-populate a form when updating an entity
     * @param id of the entity to update
     * @return redirects to a 'update-painter.xhtml' 
     */
    public String loadPainter(Integer id) {
        try {
            thePainter = model.findPainterById(id);
            painter2 = model.findPainterById(id);
            return "update-painter";
//            return "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "index";
    }

    /**
     * Calls a EJB module that is responsible for updating the painter
     * @return Redirects to 'index.xhtml' if OK, or to 'error-page.xhtml' if not
     */
    public String update() {
        try {

            painter2.setId(thePainter.getId());
            model.updatePainter(painter2);
            painter2 = new Painter();
            return "index";
        } catch (NullPointerException npe) {
            return "error-page";
        }
    }

}
