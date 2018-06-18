/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.polsl.musiol.kryspin.model.Painter;
import pl.polsl.musiol.kryspin.model.Painting;

/**
 *
 * The Stateless session bean that provides service for Painter Entity
 *
 * @author Kryspin Musiol
 *
 * @version 1.0
 */
@Stateless
public class PainterServiceBean implements PainterServiceBeanLocal {

    /**
     * persistence context of the bean
     */
    @PersistenceContext
    private EntityManager em;

    // ---------------- Create ------------------
    /**
     * Persists new Painter in the DB
     *
     * @param painter new Painter Entity class
     */
    @Override
    public void createPainter(Painter painter) {

        em.persist(painter);

    }

    // ------------------- Retrieve -------------------
    /**
     * Finds and returns Painter by its ID
     *
     * @param id - PK of the Object being searched
     * @return found Painter Entity
     */
    @Override
    public Painter findPainter(Integer id) throws NullPointerException{

//        painter = em.find(Painter.class, id);
        return em.find(Painter.class, id);
//        em.merge(painter);
//        return em.getReference(Painter.class, id);
    }

    // ------------------- Update ---------------------
    /**
     * Updates an entity by merging passing parameter if detached
     *
     * @param painter Painter to update
     * @throws IllegalArgumentException when the entity has not been found
     */
    @Override
    public void updatePainter(Painter painter) {

        //painter = em.merge(painter);
        em.merge(painter);

    }

    // ------------------- Delete ---------------------
    /**
     * Deletes a passed Paitner from DB
     *
     * @param id of the Painter to be deleted
     * @throws IllegalArgumentException when the entity has not been found
     */
    @Override
    public void removePainter(Integer id) throws IllegalArgumentException {

        Painter painter = null;

        painter = em.find(Painter.class, id);

        if (painter != null) {
            em.remove(em.merge(painter));
        }

    }

    /**
     * assigns a Painting to a Painter
     *
     * @param findPainterById id of the Painter of the Painting
     * @param painting Painting to be assigned
     */
    @Override
    public void assignPainting(Painter findPainterById, Painting painting) {

        Painter painterToBeSet = em.merge(findPainterById);
        Painting paintingToBeUpdated = em.merge(painting);
        paintingToBeUpdated.setPainter(painterToBeSet);
        //em.merge(painting);

    }

    /**
     * assigns a Painting to a Painter
     *
     * @param chosenId id of the Painter of the Painting
     * @param id id of the Painting to be assigned
     */
    @Override
    public void assignPainting(int chosenId, Integer id) {

        Painter painterToBeSet = em.find(Painter.class, chosenId);
        Painting paintingToBeUpdated = em.find(Painting.class, id);

        if (paintingToBeUpdated != null) {
            paintingToBeUpdated.setPainter(painterToBeSet);
        }

        if (painterToBeSet != null) {
            painterToBeSet.addPainting(paintingToBeUpdated);
        }

        em.merge(painterToBeSet);
        em.merge(paintingToBeUpdated);
    }

    /**
     * Finds all Painters stored in the DB
     *
     * @return list of painters persisted
     */
    @Override
    public List<Painter> findAllPainters() {
        return em.createNamedQuery(Painter.FIND_ALL).getResultList();
    }

    /**
     * Finds all painter's id in the DB
     *
     * @return list of all IDs
     */
    @Override
    public List<Integer> findAllPaintersId() {
        return em.createNamedQuery(Painter.FIND_ALL_ID).getResultList();
    }

}
