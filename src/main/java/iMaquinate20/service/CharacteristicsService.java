package iMaquinate20.service;

import iMaquinate20.models.Characteristics;
import iMaquinate20.models.Product;
import iMaquinate20.respository.ICharacteristicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("characteristics")
public class CharacteristicsService implements IService<Characteristics>{

        @Autowired
        private ICharacteristicsRepository repository;

        public CharacteristicsService(ICharacteristicsRepository repository){
            this.repository=repository;
        }
    @Override
    @Transactional(readOnly = true)
    public List<Characteristics> getAll() {
        return (List<Characteristics>) repository.findAll() ;
    }

    @Override
    public Optional<Characteristics> getById(Integer id) {
        return repository.findById(String.valueOf(id));
    }

    @Override
    @Transactional
    public Characteristics create(Characteristics characteristics) {
        try {
         Optional<Characteristics> exists = buscarPorNombreDeCatarteristica(characteristics.getTitle());

        if(exists.isPresent()){
            throw new IllegalArgumentException("la cat ya existe");
        }
        return repository.save(characteristics);

        }catch (Exception e){
            throw e;
        }
    }
    @Transactional
    private Optional<Characteristics> buscarPorNombreDeCatarteristica(String title) {
            try{
                return repository.buscarPorNombreDeCaracteristica(title);
            }catch (Exception e){
                throw e;
            }
    }

    @Override
    public Characteristics update(Integer id, Characteristics characteristics) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Product> getProductsByCategory(int id) {
        return null;
    }

    @Override
    public void sendEmail(String[] toUser, String subject, String message) {

    }

    public Optional<Characteristics> buscarCaracteristicaPorId(Integer id) {
            return repository.findById(String.valueOf(id));
    }
}
