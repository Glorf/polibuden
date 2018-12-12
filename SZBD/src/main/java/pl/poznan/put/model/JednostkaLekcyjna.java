package pl.poznan.put.model;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import javax.persistence.*;

import io.springlets.format.EntityFormat;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * = JednostkaLekcyjna
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{typ.nazwa} #{idPrzedmiotu.nazwa} #{idProwadzacy.nazwisko}, #{dzien.nazwa} #{godzinaOd}")
@RooEquals(isJpaEntity = true)
@Entity
@Table(name = "jednostka_lekcyjna", uniqueConstraints={
        @UniqueConstraint(columnNames = {"id_sali", "dzien", "godzina_od", "godzina_do"}),
        @UniqueConstraint(columnNames = {"id_prowadzacy", "dzien", "godzina_od", "godzina_do"})},
        indexes = {@Index(name = "jednostka_idx",  columnList="id", unique = true)})

@EntityFormat("#{typ.nazwa} #{idPrzedmiotu.nazwa} #{idProwadzacy.nazwisko}, #{dzien.nazwa} #{godzinaOd}")
public class JednostkaLekcyjna {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jednostkalekcyjna_generator")
    @SequenceGenerator(name="jednostkalekcyjna_generator", sequenceName = "jednostkalekcyjna_seq", allocationSize = 1)
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
    @JoinColumn(name = "id_sali")
    private Sala idSali;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @EntityFormat
    @JoinColumn(name = "id_prowadzacy")
    private Wykladowca idProwadzacy;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "jednostka_grupa", joinColumns = { @JoinColumn(name = "grupy", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "jednostki_lekcyjne", referencedColumnName = "id") })
    private Set<Grupa> grupas = new HashSet<Grupa>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "godzina_od")
    @NotNull
    @Pattern(regexp = "^[0-2]?[0-9]:[0-5][0-9]$", message = "Podaj godzinę w formacie 00:00")
    private String godzinaOd;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "godzina_do")
    @NotNull
    @Pattern(regexp = "^[0-2][0-9]:[0-5][0-9]$", message = "Podaj godzinę w formacie 00:00")
    private String godzinaDo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_przedmiotu")
    @EntityFormat
    private Przedmiot idPrzedmiotu;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typ")
    @EntityFormat
    private TypZajec typ;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @EntityFormat
    @JoinColumn(name = "dzien")
    private Dzien dzien;

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
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setId(Long id) {
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
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets idSali value
     *
     * @return Sala
     */
    public Sala getIdSali() {
        return this.idSali;
    }

    /**
     * Sets idSali value
     *
     * @param idSali
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setIdSali(Sala idSali) {
        this.idSali = idSali;
        return this;
    }

    /**
     * Gets idProwadzacy value
     *
     * @return Wykladowca
     */
    public Wykladowca getIdProwadzacy() {
        return this.idProwadzacy;
    }

    /**
     * Sets idProwadzacy value
     *
     * @param idProwadzacy
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setIdProwadzacy(Wykladowca idProwadzacy) {
        this.idProwadzacy = idProwadzacy;
        return this;
    }

    /**
     * Gets grupas value
     *
     * @return Set
     */
    public Set<Grupa> getGrupas() {
        return this.grupas;
    }

    /**
     * Sets grupas value
     *
     * @param grupas
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setGrupas(Set<Grupa> grupas) {
        this.grupas = grupas;
        return this;
    }

    /**
     * Gets godzinaOd value
     *
     * @return String
     */
    public String getGodzinaOd() {
        return this.godzinaOd;
    }

    /**
     * Sets godzinaOd value
     *
     * @param godzinaOd
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setGodzinaOd(String godzinaOd) {
        this.godzinaOd = godzinaOd;
        return this;
    }

    /**
     * Gets godzinaDo value
     *
     * @return String
     */
    public String getGodzinaDo() {
        return this.godzinaDo;
    }

    /**
     * Sets godzinaDo value
     *
     * @param godzinaDo
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setGodzinaDo(String godzinaDo) {
        this.godzinaDo = godzinaDo;
        return this;
    }

    /**
     * Gets idPrzedmiotu value
     *
     * @return Przedmiot
     */
    public Przedmiot getIdPrzedmiotu() {
        return this.idPrzedmiotu;
    }

    /**
     * Sets idPrzedmiotu value
     *
     * @param idPrzedmiotu
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setIdPrzedmiotu(Przedmiot idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
        return this;
    }

    /**
     * Gets typ value
     *
     * @return TypZajec
     */
    public TypZajec getTyp() {
        return this.typ;
    }

    /**
     * Sets typ value
     *
     * @param typ
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setTyp(TypZajec typ) {
        this.typ = typ;
        return this;
    }

    /**
     * Gets dzien value
     *
     * @return Dzien
     */
    public Dzien getDzien() {
        return this.dzien;
    }

    /**
     * Sets dzien value
     *
     * @param dzien
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna setDzien(Dzien dzien) {
        this.dzien = dzien;
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
        if (!(obj instanceof JednostkaLekcyjna)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((JednostkaLekcyjna) obj).getId());
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
        return "JednostkaLekcyjna {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", godzinaOd='" + godzinaOd + '\'' + ", godzinaDo='" + godzinaDo + '\'' + "}" + super.toString();
    }
}
