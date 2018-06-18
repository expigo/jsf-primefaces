/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.model;

import java.util.List;
import javax.ejb.EJB;
import pl.polsl.musiol.kryspin.ejb.ModelFacadeBeanLocal;

/**
 *
 * @author Kryspin Musiol
 * @version 1.0
 */
public class PainterHolder {
    
    @EJB
    private ModelFacadeBeanLocal model;
    
    private List<Painter> painters;
    private static final PainterHolder instance = new PainterHolder();
    
    private PainterHolder() {
        Painter painter;
        
        painters = model.getAllPainters();
        
    }

    public List<Painter> getPainters() {
        return painters;
    }

    public static PainterHolder getInstance() {
        return instance;
    }
    
    
}
