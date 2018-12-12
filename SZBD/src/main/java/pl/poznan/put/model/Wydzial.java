package pl.poznan.put.model;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import org.springframework.roo.addon.jpa.annotations.entity.JpaRelationType;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaRelation;
import io.springlets.format.EntityFormat;
import java.util.Objects;

import org.springframework.util.Assert;
import org.postgresql.util.PSQLException;

/**
 * = Wydzial
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{nazwa}")
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat("#{nazwa}")
@Table(name = "wydzial", indexes = {@Index(name = "wydzial_idx",  columnList="id", unique = true)})
public class Wydzial {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wydzial_generator")
    @SequenceGenerator(name="wydzial_generator", sequenceName = "wydzial_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "version", nullable = false, columnDefinition = "integer default 1")
    @Version
    private Integer version;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "nazwa", unique = true)
    @NotNull
    private String nazwa;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "wydzial")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<Zespol> zespoly = new HashSet<Zespol>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "wydzial")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<Kierunek> kierunki = new HashSet<Kierunek>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * Gets id value
     *
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id value
     *
     * @param id
     * @return Wydzial
     */
    public Wydzial setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets version value
     *
     * @return Integer
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     * Sets version value
     *
     * @param version
     * @return Wydzial
     */
    public Wydzial setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets nazwa value
     *
     * @return String
     */
    public String getNazwa() {
        return this.nazwa;
    }

    /**
     * Sets nazwa value
     *
     * @param nazwa
     * @return Wydzial
     */
    public Wydzial setNazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    /**
     * Gets zespoly value
     *
     * @return Set
     */
    public Set<Zespol> getZespoly() {
        return this.zespoly;
    }

    /**
     * Sets zespoly value
     *
     * @param zespoly
     * @return Wydzial
     */
    public Wydzial setZespoly(Set<Zespol> zespoly) {
        this.zespoly = zespoly;
        return this;
    }

    /**
     * Gets kierunki value
     *
     * @return Set
     */
    public Set<Kierunek> getKierunki() {
        return this.kierunki;
    }

    /**
     * Sets kierunki value
     *
     * @param kierunki
     * @return Wydzial
     */
    public Wydzial setKierunki(Set<Kierunek> kierunki) {
        this.kierunki = kierunki;
        return this;
    }

    /**
     * This `equals` implementation is specific for JPA entities and uses
     * the entity identifier for it, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @param obj
     * @return Boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof Wydzial)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Wydzial) obj).getId());
    }

    /**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able
     * to identify the entity in collections after a new id is assigned to the entity, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @return Integer
     */
    public int hashCode() {
        return 31;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String toString() {
        return "Wydzial {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", nazwa='" + nazwa + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespolyToAdd
     */
    public void addToZespoly(Iterable<Zespol> zespolyToAdd) {
        Assert.notNull(zespolyToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (Zespol item : zespolyToAdd) {
            this.zespoly.add(item);
            item.setWydzial(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespolyToRemove
     */
    public void removeFromZespoly(Iterable<Zespol> zespolyToRemove) {
        Assert.notNull(zespolyToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Zespol item : zespolyToRemove) {
            this.zespoly.remove(item);
            item.setWydzial(null);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunkiToAdd
     */
    public void addToKierunki(Iterable<Kierunek> kierunkiToAdd) {
        Assert.notNull(kierunkiToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (Kierunek item : kierunkiToAdd) {
            this.kierunki.add(item);
            item.setWydzial(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunkiToRemove
     */
    public void removeFromKierunki(Iterable<Kierunek> kierunkiToRemove) {
        Assert.notNull(kierunkiToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Kierunek item : kierunkiToRemove) {
            this.kierunki.remove(item);
            item.setWydzial(null);
        }
    }
}
