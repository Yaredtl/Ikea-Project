package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Provincia;
import myikea.yared.aut05_01.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciaServiceImp implements ProvinciaServices{
    @Autowired
    ProvinciaRepository provinciaRepository;

    public List<Provincia> listProvincia (){
        return provinciaRepository.findAll();
    };
    public Optional<Provincia> getProvincia(int id){
        return provinciaRepository.findById(id);
    };
}
