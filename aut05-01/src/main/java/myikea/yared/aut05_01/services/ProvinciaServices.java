package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Provincia;

import java.util.List;
import java.util.Optional;

public interface ProvinciaServices {
    List<Provincia> listProvincia();
    Optional<Provincia> getProvincia(int id);
}
