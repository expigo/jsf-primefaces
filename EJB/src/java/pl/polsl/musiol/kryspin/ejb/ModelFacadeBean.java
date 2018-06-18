/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.ejb;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.polsl.musiol.kryspin.model.Painter;
import pl.polsl.musiol.kryspin.model.Painting;

/**
 *
 * The Stateless Session Bean that injects PainterServiceBeanLocal and PaintingServiceBeanLocal interfaces, 
 * provide a helping 'facade'
 * 
 * @author Kryspin Musiol
 * 
 * @version 1.0
 */
@Stateless
@EJBs({
    @EJB(beanInterface = PainterServiceBeanLocal.class, name = "painterService")
    ,
    @EJB(beanInterface = PaintingServiceBeanLocal.class, name = "paintingService")})

public class ModelFacadeBean implements ModelFacadeBeanLocal {

//    @EJB
//    private static PainterServiceBeanLocal painterService;
//    
//    @EJB
//    private static PaintingServiceBeanLocal paintingService;
    //PainterServiceBeanRemote painterService = (PainterServiceBeanLocal) (new InitialContext()).lookup("painterServiceBeanRemote");
    /**
     * Persist context of the bean
     */
    @PersistenceContext
    private EntityManager em;


    /**
     * calls painterService that is responsible for persisting the passed
     * parameter
     *
     * @param painter to be persisted in DB
     */
    @Override
    public void createPainter(Painter painter) {
        try {
            InitialContext ctx = new InitialContext();
            //PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) ctx.lookup("java:global/Lab2-EAR/Lab2-EAR-ejb/PainterServiceBean!pl.musiol.kryspin.ejb.PainterServiceBeanRemote");
            PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) ctx.lookup("java:comp/env/painterService");
            painterService.createPainter(painter);
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }
    }

    /**
     * calls paintingService method responisible for persisting the passed
     * parameter
     *
     * @param painting to be persisted in DB
     * @return Id of newly persisted painter if OK, else returns null
     */
    @Override
    public Integer createPainting(Painting painting) {
        try {
            InitialContext ctx = new InitialContext();
            PaintingServiceBeanLocal paintingService = (PaintingServiceBeanLocal) ctx.lookup("java:comp/env/paintingService");
            return paintingService.createPainting(painting);
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }

        return null;
    }

    /**
     * calls painterQueryExecutor method that runs a query againgst a DB
     *
     * @return List of all Painters stored in DB
     */
    @Override
    public List<Painter> getAllPainters() {
        try {
            InitialContext ctx = new InitialContext();
            PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) ctx.lookup("java:comp/env/painterService");
            return painterService.findAllPainters();
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }

        return null;
    }

    /**
     * calls paintingQueryExecutor method that runs a query againgst a DB
     *
     * @return List of all Paintings stored in DB
     */
    @Override
    public List<Painting> getAllPaintings() {
        try {
            InitialContext ctx = new InitialContext();
            PaintingServiceBeanLocal paintingService = (PaintingServiceBeanLocal) ctx.lookup("java:comp/env/paintingService");
            return paintingService.findAll();
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }

        return null;
    }

    /**
     * calls painterQueryExecutor method that runs a query againgst a DB
     *
     * @return List of all Painter's Ids in the DB
     */
    @Override
    public List<Integer> getAllPaintersIds() {
        try {
            InitialContext ctx = new InitialContext();
            PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) ctx.lookup("java:comp/env/painterService");
            return painterService.findAllPaintersId();
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }

        return null;
    }

    /**
     * calls painterQueryExecutor method that runs a query againgst a DB
     * searching for all Painting's painted by chosen Painter
     *
     * @param chosenPainter id of the Painter whose Paintings are about to be
     * searched
     * @return List of all Paintings painted by a given Painter
     */
    @Override
    public List<Painting> getPainterPaintings(int chosenPainter) {

        try {
            InitialContext ctx = new InitialContext();
            PaintingServiceBeanLocal paintingService = (PaintingServiceBeanLocal) ctx.lookup("java:comp/env/paintingService");
            return paintingService.findAllPainterPaintings(chosenPainter);
        } catch (NamingException ex) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return null;
    }

    /**
     * calls paintingQueryExecutor method that runs a query againgst a DB
     *
     * @return List of all Paintings with title LIKE M%
     */
    @Override
    public List<Painting> getPaintingsStartingWith() {

        try {
            InitialContext ctx = new InitialContext();
            PaintingServiceBeanLocal paintingService;
            paintingService = (PaintingServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/paintingService");
            return paintingService.findAllStartingWitLetter();
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }

        return null;
    }

    /**
     * calls painterService method responsible for updating chosen Painter
     *
     * @param painterUpdated entity to be updated
     */
    @Override
    public void updatePainter(Painter painterUpdated) {
        try {
            InitialContext ctx = new InitialContext();
            PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/painterService");

            painterService.updatePainter(painterUpdated);
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }
    }

    /**
     * calls painterService method responsible for finding the chosen painter by
     * it's id
     *
     * @param chosenPainterUpdate id of the painter to be found
     * @return the found Painter, null if the entity does not exist
     */
    @Override
    public Painter findPainterById(int chosenPainterUpdate) {
        try {
            InitialContext ctx = new InitialContext();
            PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/painterService");

            return painterService.findPainter(chosenPainterUpdate);
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        } catch (NullPointerException npe) {
            throw new NullPointerException("<<<<<<>>>>> NPE at:" + getClass().getName());
        }

        return null;
    }

    /**
     * calls paintingService method responsible for finding the chosen painting
     * by it's id
     *
     * @param chosenPaintingUpdate id of the painting to be found
     * @return the found Painting, null if the entry does not exist
     */
    @Override
    public Painting findPaintingById(int chosenPaintingUpdate) {
        try {
            InitialContext ctx = new InitialContext();
            PaintingServiceBeanLocal paintingService = (PaintingServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/paintingService");
            return paintingService.findPainting(chosenPaintingUpdate);
        } catch (NamingException ex) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * calls paintingQueryExecutor method that runs a query againgst a DB
     * searching for all Painting's ids
     *
     * @return List of found ids
     */
    @Override
    public List<Integer> getAllPaintingsIds() {
        try {
            InitialContext ctx = new InitialContext();
            PaintingServiceBeanLocal paintingService = (PaintingServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/paintingService");
            return paintingService.findAllPaintingsIds();
        } catch (NamingException ex) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * calls painterService method responsible for updating chosen Painter
     *
     * @param paintingUpdated The painting to be updated
     */
    @Override
    public void updatePainting(Painting paintingUpdated) {
        try {
            InitialContext ctx = new InitialContext();
            PaintingServiceBeanLocal paintingService = (PaintingServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/paintingService");
            paintingService.updatePainting(paintingUpdated);
        } catch (NamingException ex) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Calls a painterService method for removing from DB the Painter entity
     * with specified id
     *
     * @param chosenPainterDelete id of the Painter to be deleted
     */
    @Override

    public void deletePainter(int chosenPainterDelete
    ) {
        try {
            InitialContext ctx = new InitialContext();
            PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/painterService");
            painterService.removePainter(chosenPainterDelete);
        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }
    }

    /**
     * Calls a paintingService method for removing from DB the Painter entity
     * with specified id
     *
     * @param chosenPaintingDelete id of the Painting to be deleted
     */
    @Override
    public void deletePainting(int chosenPaintingDelete) {
        try {
            InitialContext ctx = new InitialContext();
            PaintingServiceBeanLocal paintingService = (PaintingServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/paintingService");

            paintingService.removePainting(chosenPaintingDelete);
        } catch (NamingException ex) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Calls a painterService method that assigns a newly created Painting to
     * it's Painter
     *
     * @param findPainterById id of the Painter that will be assigned with new
     * Painting
     * @param painting Painting that needs a Painter
     */
    @Override
    public void assignPaintingToPainter(Painter findPainterById, Painting painting) {
        try {
            InitialContext ctx = new InitialContext();
            PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) (new InitialContext()).lookup("java:comp/env/painterService");

            painterService.assignPainting(findPainterById, painting);

        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }
    }

    /**
     * Calls a painterService method that assigns a newly created Painting to
     * it's Painter
     *
     * @param chosenId id of the Painter that will be assigned with new Painting
     * @param id id of the Painting that needs a Painter
     */
    @Override
    public void assignPaintingToPainter(int chosenId, Integer id) {
        try {
            System.out.println("chosenId: " + chosenId + "id: " + id);
            InitialContext ctx = new InitialContext();
            PainterServiceBeanLocal painterService = (PainterServiceBeanLocal) ctx.lookup("java:comp/env/painterService");
            painterService.assignPainting(chosenId, id);


        } catch (NamingException ne) {
            Logger.getLogger(ModelFacadeBean.class.getName()).log(Level.SEVERE, null, ne);
        }
    }
}
