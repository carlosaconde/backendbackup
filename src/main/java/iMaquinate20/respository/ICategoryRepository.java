package iMaquinate20.respository;

import iMaquinate20.models.Category;
import iMaquinate20.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICategoryRepository extends CrudRepository<Category,String> {

    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
    Optional<Category> buscarPorNombreDeCategoria(@Param("categoryName") String categoryName);
}
