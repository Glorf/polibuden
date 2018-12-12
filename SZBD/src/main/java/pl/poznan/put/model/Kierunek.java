package pl.poznan.put.model;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import javax.persistence.*;

import io.springlets.format.EntityFormat;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import org.springframework.roo.addon.jpa.annotations.entity.JpaRelationType;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaRelation;
import java.util.Objects;

import org.springframework.util.Assert;

/**
 * = Kierunek
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{nazwa}")
@RooEquals(isJpaEntity = true)
@Entity
@Table(name = "kierunek", uniqueConstraints={
        @UniqueConstraint(columnNames = {"wydzial", "nazwa"})},
        indexes = {@Index(name = "kierunek_idx",  columnList="id", unique = true)})

@EntityFormat("#{nazwa}")
public class Kierunek {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kierunek_generator")
    @SequenceGenerator(name="kierunek_generator", sequenceName = "kierunek_seq", allocationSize = 1)
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
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @EntityFormat
    @JoinColumn(name = "wydzial")
    private Wydzial wydzial;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "nazwa")
    @NotNull
    private String nazwa;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "kierunek")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<Grupa> grupy = new HashSet<Grupa>();

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
     * @return Kierunek
     */
    public Kierunek setId(Long id) {
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
     * @return Kierunek
     */
    public Kierunek setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets wydzial value
     *
     * @return Wydzial
     */
    public Wydzial getWydzial() {
        return this.wydzial;
    }

    /**
     * Sets wydzial value
     *
     * @param wydzial
     * @return Kierunek
     */
    public Kierunek setWydzial(Wydzial wydzial) {
        this.wydzial = wydzial;
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
     * @return Kierunek
     */
    public Kierunek setNazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    /**
     * Gets grupy value
     *
     * @return Set
     */
    public Set<Grupa> getGrupy() {
        return this.grupy;
    }

    /**
     * Sets grupy value
     *
     * @param grupy
     * @return Kierunek
     */
    public Kierunek setGrupy(Set<Grupa> grupy) {
        this.grupy = grupy;
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
        if (!(obj instanceof Kierunek)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Kierunek) obj).getId());
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
        return "Kierunek {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", nazwa='" + nazwa + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupyToAdd
     */
    public void addToGrupy(Iterable<Grupa> grupyToAdd) {
        Assert.notNull(grupyToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (Grupa item : grupyToAdd) {
            this.grupy.add(item);
            item.setKierunek(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupyToRemove
     */
    public void removeFromGrupy(Iterable<Grupa> grupyToRemove) {
        Assert.notNull(grupyToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Grupa item : grupyToRemove) {
            this.grupy.remove(item);
            item.setKierunek(null);
        }
    }
}
