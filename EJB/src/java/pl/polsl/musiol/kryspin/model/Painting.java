/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Painting entity class.
 *
 * @author Kryspin Musiol
 * @version 1.0
 */
@Entity
@Table(name = "painting")
@NamedQueries({
    @NamedQuery(name = Painting.FIND_ALL, query = "SELECT p FROM Painting p")
    ,
@NamedQuery(name = Painting.FIND_PAINTINGS_BY_PAINTER, query = "SELECT p FROM Painting p WHERE p.painter.id = :custId")
    ,
@NamedQuery(name = Painting.FIND_ALL_ID, query = "SELECT p.id FROM Painting p")
})
public class Painting implements Serializable {

    /**
     * Query name for finding all painters
     */
    public static final String FIND_ALL = "Painting.findPainter";

    /**
     * Query name for finding all paintings painted by specific painter
     */
    public static final String FIND_PAINTINGS_BY_PAINTER = "Painting.findPainterByPainter";

    /**
     * Query name for query finding IDs of all stored Paintings
     */
    public static final String FIND_ALL_ID = "Painting.findIds";

    /**
     * Painting's auto generated ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Painting's title
     */
    @Column(name = "title")
    private String title;

    /**
     * Painting's painter
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "painter_id")
    private Painter painter;

    // ----------------- Getters & Setters ------------------

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public Painter getPainter() {
        return painter;
    }

    /**
     *
     * @param painter
     */
    public void setPainter(Painter painter) {
        this.painter = painter;
//        painter.getPaintings().add(this);
    }

    // ------------------ overriden methods -------------------

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Painting other = (Painting) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        //return "Painter{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", age=" + age + '}';
        StringBuilder sb = new StringBuilder();

        sb.append(title).append(": ");
        sb.append(painter).append(" [id:" + id + "]");

        return sb.toString();
    }

}
