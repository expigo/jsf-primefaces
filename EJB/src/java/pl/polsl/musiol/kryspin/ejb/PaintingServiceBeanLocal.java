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
 * The Local interface defining crud methods for Painting entity
 * 
 * @author Kryspin Musiol
 * @version 1.0
 */
@Local
public interface PaintingServiceBeanLocal {

    // ---------------- Create ------------------
    /**
     * Persists a new painting in the DB
     *
     * @param painting new Painting entity class
     * @return Id of the Painting persisted
     */
    public Integer createPainting(Painting painting);

    // ------------------- Retrieve -------------------
    /**
     * Finds a Painting in DB with given id
     *
     * @param id of the Painting to be found
     * @return found Painting with the specfied id
     */
    public Painting findPainting(Integer id);

    // ------------------- Update ---------------------
    /**
     * Updates a Painter stored in DB
     *
     * @param painting to be merged
     */
    public void updatePainting(Painting painting);

    // ------------------- Delete --------------------
    /**
     * deletes a Painting with specified id from the Db
     *
     * @param id of the Painting to be deleted
     * @throws IllegalArgumentException when the entity has not been found
     */
    public void removePainting(Integer id);
    
    /**
     * uses hard-coded JPQL looking for all Paintings stored in DB
     *
     * @return list of all paintings
     */
    public List<Painting> findAll();

    /**
     * @param id id of the Paintings for which the Painter is being searched
     * @return uthor of the specified Painting
     */
    public Painter getAuthor(Integer id);

    /**
     * Looks all paintings that's title starting with letter M
     *
     * @return list of Paintings satysfying the query
     */
    public List<Painting> findAllStartingWitLetter();

    /**
     * Finds all paintings created by the chosen painter
     *
     * @param id of the painter that paintings are being searched
     * @return List of all Painting in the DB
     */
    public List<Painting> findAllPainterPaintings(Integer id);

    /**
     * Finds all IDs of Painters stored
     *
     * @return list of all painter's IDs in the Table
     */
    List<Integer> findAllPaintingsIds();


}
