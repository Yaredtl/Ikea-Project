package myikea.yared.aut05_01.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import myikea.yared.aut05_01.models.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    List<Municipio> findByProvinciaId(Long provinciaId);
}

