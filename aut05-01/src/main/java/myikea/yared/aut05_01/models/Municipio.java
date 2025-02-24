package myikea.yared.aut05_01.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="municipios")
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_municipio")
    private int id;

    @NotNull
    @Column(name="id_provincia")
    private int idProvincia;

    @NotNull
    @Column(name="cod_municipio")
    private int codMunicipio;

    @NotNull
    @Column(name="DC")
    private int dc;

    @NotNull
    @Column(name="nombre")
    private String name;

    @ManyToOne
    private Provincia provincia;

    public Municipio() {
    }

    public Municipio(int id, Provincia provincia, String name, int dc, int codMunicipio, int idProvincia) {
        this.id = id;
        this.provincia = provincia;
        this.name = name;
        this.dc = dc;
        this.codMunicipio = codMunicipio;
        this.idProvincia = idProvincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public int getDc() {
        return dc;
    }

    public void setDc(@NotNull int dc) {
        this.dc = dc;
    }

    @NotNull
    public int getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(@NotNull int codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    @NotNull
    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(@NotNull int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}


