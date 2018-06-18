/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.ejb;

import java.util.List;
import javax.ejb.Local;
import pl.polsl.musiol.kryspin.model.Painter;
import pl.polsl.musiol.kryspin.model.Painting;

/**
 * The Local interface defining crud methods for Painter entity
 * 
 * @author Kryspin Musiol
 * 
 * @version 1.0
 */
@Local
public interface PainterServiceBeanLocal {

    
    // ---------------- Create ------------------
    /**
     * Persists new Painter in the DB
     *
     * @param painter new Painter Entity class
     */
    public void createPainter(Painter painter);

    // ------------------- Retrieve -------------------
    /**
     * Finds and returns Painter by its ID
     *
     * @param id - PK of the Object being searched
     * @return found Painter Entity
     */
    public Painter findPainter(Integer id) throws NullPointerException;

    // ------------------- Update ---------------------
    /**
     * Updates ae entity by merging passing parameter if detached
     *
     * @param painter Painter to update
     * @throws IllegalArgumentException when the entity has not been found
     */
    public void updatePainter(Painter painter);
    // ------------------- Delete ---------------------
    /**
     * Deletes a passed Paitner from DB
     *
     * @param id of the Painter to be deleted
     * @throws IllegalArgumentException when the entity has not been found in the DB
     */
    public void removePainter(Integer id);

    /**
     * assigns a Painting to a Painter
     *
     * @param findPainterById id of the Painter of the Painting
     * @param painting Painting to be assigned
     */
    void assignPainting(Painter findPainterById, Painting painting);

    /**
     * assigns a Painting to a Painter
     *
     * @param chosenId id of the Painter of the Painting
     * @param id id of the Painting to be assigned
     */
    void assignPainting(int chosenId, Integer id);
    
    
        /**
     * Finds all Painters stored in the DB
     *
     * @return list of painters persisted
     */
    public List<Painter> findAllPainters();

    /**
     * Finds all painter's id in the DB
     *
     * @return list of all IDs
     */
    public List<Integer> findAllPaintersId();
}
