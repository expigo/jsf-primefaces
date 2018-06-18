/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.musiol.kryspin.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Painter entity class.
 *
 * @author Kryspin Musiol
 * @version 1.0
 */
@Entity
@Table(name = "painter")
@EntityListeners({
    NameValidationListener.class,
    AgeCalculationListener.class
})
@NamedQueries({
    @NamedQuery(name = Painter.FIND_ALL, query = "SELECT p FROM Painter p")
    ,
    @NamedQuery(name = Painter.FIND_ALL_ID, query = "SELECT p.id FROM Painter p"), //@NamedQuery(name = Painter.FIND_PAINTINGS, query = "SELECT pg FROM Painting pg WHERE pg.painter :custId")
})
public class Painter implements Serializable {

    /**
     *
     */
    public static final String FIND_ALL = "Painter.findAll";

    /**
     *
     */
    public static final String FIND_ALL_ID = "Painter.findAllId";

    /**
     *
     */
    public static final String FIND_PAINTINGS = "Painter.findPaintings";

    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Painter's first name
     */
    @Column(name = "first_name", length = 25)
    private String firstName;

    /**
     * Painter's last name
     */
    @Column(name = "last_name", length = 50)
    private String lastName;
    /**
     * Painter's date of birth (format: dd/MM/yyyy)
     */
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    /**
     * Painter's age. Annotated with @Transient. It's value is calculated with
     * use of AgeCalculationListener Entity Listener
     */
    @Transient
    private Integer age;

    /**
     * Represent's bidirectional one-to-many relationship with Painting
     */
    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "painter",
            cascade = {CascadeType.ALL})
    private List<Painting> paintings;

    /**
     *
     * @return when the entity has not been found
     */
    public List<Painting> getPaintings() {
        return paintings;
    }

    /**
     *
     * @param paintings
     */
    public void setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
    }

    // ************ GETTERS & SETTERS **************

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
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     *
     * @param painting
     */
    public void addPainting(Painting painting) {

        if (paintings == null) {
            paintings = new ArrayList<>();
        }

        paintings.add(painting);
    }

    //************* OVERRIDEN METHODS *************

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Painter other = (Painter) obj;
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

        sb.append("[" + id + "]");
        sb.append(firstName).append(" ");
        sb.append(lastName);
        if (dateOfBirth != null) {
            sb.append(" ");
            sb.append(age);
        }

        return sb.toString();
    }

}
