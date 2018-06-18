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
 * The local facade interface gathering all crud methods for all entities
 * 
 * @author Kryspin Musiol
 * 
 * @version 1.0
 */
@Local
public interface ModelFacadeBeanLocal {

    /**
     * calls painterService that is responsible for persisting the passed parameter
     * @param painter to be persisted in DB
     */       
    public void createPainter(Painter painter);

    /**
     * calls paintingService method responisible for persisting the passed parameter
     * @param painting to be persisted in DB
     * @return id (Integer) of the bewly created painting
     */
    public Integer createPainting(Painting painting);

    /**
     * calls painterQueryExecutor method that runs a query againgst a DB
     * @return List of all Painters stored in DB
     */
    public List<Painter> getAllPainters();

    /**
     * calls paintingQueryExecutor method that runs a query againgst a DB
     * @return List of all Paintings stored in DB
     */
    public List<Painting> getAllPaintings();

    /**
     * calls painterQueryExecutor method that runs a query againgst a DB
     * @return List of all Painter's Ids in the DB
     */
    public List<Integer> getAllPaintersIds();
    
    /**
     * calls painterQueryExecutor method that runs a query againgst a DB searching for all Painting's painted by chosen Painter
     * @param chosenPainter id of the Painter whose Paintings are about to be searched
     * @return List of all Paintings painted by a given Painter
     */
    public List<Painting> getPainterPaintings(int chosenPainter);

    /**
     * calls paintingQueryExecutor method that runs a query againgst a DB
     * @return List of all Paintings with title LIKE M%
     */
    public List<Painting> getPaintingsStartingWith();

    /**
     * calls painterService method responsible for updating chosen Painter
     * @param painterUpdated entity to be updated
     */
    public void updatePainter(Painter painterUpdated);


    /**
     * calls painterService method responsible for finding the chosen painter by it's id
     * @param chosenPainterUpdate id of the painter to be found
     * @return the found Painter, null if the entity does not exist
     */
    public Painter findPainterById(int chosenPainterUpdate);

    /**
     * calls paintingService method responsible for finding the chosen painting by it's id
     * @param chosenPaintingUpdate id of the painting to be found
     * @return the found Painting, null if the entry does not exist
     */
    public Painting findPaintingById(int chosenPaintingUpdate);

    /**
     * calls paintingQueryExecutor method that runs a query againgst a DB searching for all Painting's ids
     * @return List of found ids
     */
    public List<Integer> getAllPaintingsIds();

    /**
     * calls painterService method responsible for updating chosen Painter
     * @param paintingUpdated The painting to be updated
     */
    public void updatePainting(Painting paintingUpdated);
    
    /**
     * Calls a painterService method for removing from DB the Painter entity with specified id
     * @param chosenPainterDelete id of the Painter to be deleted
     */
    public void deletePainter(int chosenPainterDelete);

    /**
     * Calls a paintingService method for removing from DB the Painter entity with specified id
     * @param chosenPaintingDelete id of the Painting to be deleted
     */
    public void deletePainting(int chosenPaintingDelete);
    
    /**
     * Calls a painterService method that assigns a newly created Painting to it's Painter
     * @param findPainterById id of the Painter that will be assigned with new Painting
     * @param painting Painting that needs a Painter
     */
    public void assignPaintingToPainter(Painter findPainterById, Painting painting);
    /**
     * Calls a painterService method that assigns a newly created Painting to it's Painter
     * @param chosenId id of the Painter that will be assigned with new Painting
     * @param id id of the Painting that needs a Painter
     */
    public void assignPaintingToPainter(int chosenId, Integer id);
}
