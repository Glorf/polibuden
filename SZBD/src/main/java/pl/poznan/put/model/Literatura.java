package pl.poznan.put.model;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;
import io.springlets.format.EntityFormat;
import java.util.Objects;

/**
 * = Literatura
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{autor}, \"#{tytul}\"")
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat("#{autor}, \"#{tytul}\"")
@Table(name = "literatura", indexes = {@Index(name = "literatura_idx",  columnList="id", unique = true)})
public class Literatura {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "literatura_generator")
    @SequenceGenerator(name="literatura_generator", sequenceName = "literatura_seq", allocationSize = 1)
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "przedmiot_literatura", joinColumns = { @JoinColumn(name = "literatura", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "przedmioty", referencedColumnName = "id") })
    private Set<Przedmiot> przedmioty = new HashSet<Przedmiot>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "isbn", unique = true)
    @NotNull
    private Integer isbn;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "autor")
    @NotNull
    private String autor;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "tytul")
    @NotNull
    private String tytul;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "wydawnictwo")
    @NotNull
    private String wydawnictwo;

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
     * @return Literatura
     */
    public Literatura setId(Long id) {
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
     * @return Literatura
     */
    public Literatura setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets przedmioty value
     *
     * @return Set
     */
    public Set<Przedmiot> getPrzedmioty() {
        return this.przedmioty;
    }

    /**
     * Sets przedmioty value
     *
     * @param przedmioty
     * @return Literatura
     */
    public Literatura setPrzedmioty(Set<Przedmiot> przedmioty) {
        this.przedmioty = przedmioty;
        return this;
    }

    /**
     * Gets isbn value
     *
     * @return Integer
     */
    public Integer getIsbn() {
        return this.isbn;
    }

    /**
     * Sets isbn value
     *
     * @param isbn
     * @return Literatura
     */
    public Literatura setIsbn(Integer isbn) {
        this.isbn = isbn;
        return this;
    }

    /**
     * Gets autor value
     *
     * @return String
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * Sets autor value
     *
     * @param autor
     * @return Literatura
     */
    public Literatura setAutor(String autor) {
        this.autor = autor;
        return this;
    }

    /**
     * Gets tytul value
     *
     * @return String
     */
    public String getTytul() {
        return this.tytul;
    }

    /**
     * Sets tytul value
     *
     * @param tytul
     * @return Literatura
     */
    public Literatura setTytul(String tytul) {
        this.tytul = tytul;
        return this;
    }

    /**
     * Gets wydawnictwo value
     *
     * @return String
     */
    public String getWydawnictwo() {
        return this.wydawnictwo;
    }

    /**
     * Sets wydawnictwo value
     *
     * @param wydawnictwo
     * @return Literatura
     */
    public Literatura setWydawnictwo(String wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
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
        if (!(obj instanceof Literatura)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Literatura) obj).getId());
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
        return "Literatura {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", isbn='" + isbn + '\'' + ", autor='" + autor + '\'' + ", tytul='" + tytul + '\'' + ", wydawnictwo='" + wydawnictwo + '\'' + "}" + super.toString();
    }
}
