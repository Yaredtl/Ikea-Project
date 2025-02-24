package myikea.yared.aut05_01.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_provincia")
    private int id;

    @NotNull
    @Column(name="nombre")
    private String name;

    @OneToMany(mappedBy = "provincia",
            cascade = CascadeType.ALL,
            orphanRemoval = false)
    private List<Municipio> municipios;

    public Provincia() {
    }

    public Provincia(int id, String name, List<Municipio> municipios) {
        this.id = id;
        this.name = name;
        this.municipios = municipios;
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

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
}
