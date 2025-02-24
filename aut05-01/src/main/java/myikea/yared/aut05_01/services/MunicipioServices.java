package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Municipio;

import java.util.List;
import java.util.Optional;

public interface MunicipioServices {
    List<Municipio> listMunicipio ();
    Optional<Municipio> getMunicipio(int id);
    List<Municipio> getByProvinciaId(Long provinciaId);
}
