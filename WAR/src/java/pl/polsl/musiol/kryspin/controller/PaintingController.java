/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;
import pl.polsl.musiol.kryspin.ejb.ModelFacadeBeanLocal;
import pl.polsl.musiol.kryspin.model.Painter;
import pl.polsl.musiol.kryspin.model.Painting;

/**
 *
 * Bean that handles the request coming Paintings.
 * 
 * @author Kryspin Musiol
 * @version 38.66
 * 
 * 
 */
@ManagedBean
@SessionScoped
public class PaintingController implements Serializable {

    @EJB
    private ModelFacadeBeanLocal model;
    
    private static final Logger LOGGER = Logger.getLogger(PainterController.class.getName());

    /**
     * List of all painters in the DB
     */
    private List<Painter> painters;
    
    /**
     * The painter object to work on.
     */
    private Painter painter;
    
    /**
     * A title that is going to be set to new Painting.
     */
    private String title;

    /**
     * List of all paintings
     */
    private List<Painting> paintings;
    
    /**
     * Painting used to perform CRUD operations
     */
    private Painting painting;
    /**
     * Painting to be updated
     */
    private Painting paintingToUpdate;


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

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public Painting getPainting() {
        return painting;
    }

    public void setPainting(Painting painting) {
        this.painting = painting;
    }

    public Painting getPaintingToUpdate() {
        return paintingToUpdate;
    }

    public void setPaintingToUpdate(Painting paintingToUpdate) {
        this.paintingToUpdate = paintingToUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    

    /**
     * Method that is called after construction of PainterController class
     * Mainly updates the members of the class with values taken from the DB
     */
    @PostConstruct
    public void init() {

        painting = new Painting();
        paintingToUpdate = new Painting();
        painter = new Painter();
        painters = model.getAllPainters();
        paintings = model.getAllPaintings();
    }

    /**
     * Function called in order to fill the list of the painters
     */
    public void loadPaintings() {

        paintings.clear();
            paintings = model.getAllPaintings();


        //return "list-painters?faces-redirect=true";
    }

    /**
     * Method that calls proper function from EJB module to store the Painting in the DB
     * @return Redirects to list of all paintings
     */
    public String addPainting() {

            model.createPainting(painting);
            painter = new Painter();
            painting = new Painting();
            paintings = model.getAllPaintings();
            return "list-paintings";


    }

    /**
     * Method that calls proper function from EJB module to delete the Painting in the DB
     * @param painting Painting to be deleted
     * @return Redirects to 'list-painitngs.xhtml'
     */
    public String deletePainting(Painting painting) {

            model.deletePainting(painting.getId());
            paintings = model.getAllPaintings();
            return "list-paintings";

    }

    /**
     * Pre-populates the form with the actual title of the Painting on Update action
     * @param id of the Painting to update
     * @return Redirects to 'update-painting.xhtml'
     */
    public String update(Integer id) {

        this.painting = model.findPaintingById(id);
        this.paintingToUpdate = model.findPaintingById(id);
 
        return "update-painting";

    }

    /**
     * Method that calls proper function from EJB module to update the Painting in the DB
     * @return Redirects to 'list-paintings.xhtml'
     */
    public String update() {
        
        paintingToUpdate.setId(painting.getId());
        paintingToUpdate.setTitle(title);
        
        model.updatePainting(paintingToUpdate);
        painting = new Painting();
        return "list-paintings";

    }

}
