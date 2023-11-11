package iMaquinate20.respository;

import iMaquinate20.models.Category;
import iMaquinate20.models.Characteristics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICharacteristicsRepository extends CrudRepository<Characteristics,String> {

    @Query("SELECT c FROM Characteristics c WHERE c.title = :title")
    Optional<Characteristics> buscarPorNombreDeCaracteristica(@Param("title") String title);
}
