/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import pl.polsl.musiol.kryspin.model.Painter;
import pl.polsl.musiol.kryspin.model.Painting;

/**
 * The session bean that provides service for Painting Entity
 *
 * @author Kryspin Musiol
 * @version 1.0
 */
@Stateless
public class PaintingServiceBean implements PaintingServiceBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // ---------------- Create ------------------
    /**
     * Persists a new painting in the DB
     *
     * @param painting new Painting entity class
     * @return an ID of the persisted painting
     */
    @Override
    public Integer createPainting(Painting painting) {
        if(painting.getPainter() != null) {
            painting.setPainter(em.find(Painter.class, painting.getPainter().getId()));
        }
        em.persist(painting);
        em.flush();

        return painting.getId();

    }

    // ------------------- Retrieve -------------------
    /**
     * Finds a Painting in DB with given id
     *
     * @param id of the Painting to be found
     * @return found Painting with the specfied id
     */
    @Override
    public Painting findPainting(Integer id) {

        return em.find(Painting.class, id);

    }

    // ------------------- Update ---------------------
    /**
     * Updates a Painter stored in DB
     *
     * @param painting to be merged
     */
    @Override
    public void updatePainting(Painting painting) {

        em.merge(painting);
        em.flush();
    }

    // ------------------- Delete --------------------
    /**
     * deletes a Painting with specified id from the Db
     *
     * @param id of the Painting to be deleted
     * @throws IllegalArgumentException when the entity is not found
     */
    @Override
    public void removePainting(Integer id) {

        Painting painting = null;

        painting = findPainting(id);
        if (painting != null) {
            em.remove(em.merge(painting));
        }
    }

    /**
     * uses hard-coded JPQL looking for all Paintings stored in DB
     *
     * @return list of all paintings
     */
    @Override
    public List<Painting> findAll() {
        return em.createQuery("SELECT pg FROM Painting pg").getResultList();
    }

    /**
     * @param id id of the Paintings for which the Painter is being searched
     * @return uthor of the specified Painting
     */
    @Override
    public Painter getAuthor(Integer id) {
        TypedQuery<Painter> query = em.createNamedQuery(Painting.FIND_ALL, Painter.class).setParameter(1, id);
        Painter painter = query.getSingleResult();

        return painter;
    }

    /**
     * Looks all paintings that's title starting with letter M
     *
     * @return list of Paintings satysfying the query
     */
    @Override
    public List<Painting> findAllStartingWitLetter() {

        Root<Painting> root;
        Expression<String> title;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Painting> cq = cb.createQuery(Painting.class);

        root = cq.from(Painting.class);
        cq.select(root);
        title = root.get("title");
        cq.where(cb.like(title, "M%"));
        return em.createQuery(cq).getResultList();

    }

    /**
     * Finds all paintings created by the chosen painter
     *
     * @param id of the painter that paintings are being searched
     * @return List of all the paintings stored in the DB
     */
    @Override
    public List<Painting> findAllPainterPaintings(Integer id) {
        return em.createNamedQuery(Painting.FIND_PAINTINGS_BY_PAINTER).setParameter("custId", id).getResultList();
    }

    /**
     * Finds all IDs of Painters stored
     *
     * @return list of all painter's IDs in the Table
     */
    @Override
    public List<Integer> findAllPaintingsIds() {
        return em.createNamedQuery(Painting.FIND_ALL_ID).getResultList();
    }

}
