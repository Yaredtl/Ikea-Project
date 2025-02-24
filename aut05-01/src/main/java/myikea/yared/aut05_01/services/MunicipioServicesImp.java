package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Municipio;
import myikea.yared.aut05_01.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MunicipioServicesImp implements  MunicipioServices{
    @Autowired
    MunicipioRepository municipioRepository;

    public List< Municipio> listMunicipio (){
        return municipioRepository.findAll();
    };
    public Optional< Municipio> getMunicipio(int id){
        return municipioRepository.findById(id);
    }


    public List<Municipio> getByProvinciaId(Long provinciaId) {
        return municipioRepository.findByProvinciaId(provinciaId);
    }
}
