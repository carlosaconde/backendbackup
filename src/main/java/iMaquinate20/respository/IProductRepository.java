package iMaquinate20.respository;
import iMaquinate20.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends CrudRepository<Product, Integer> {



    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Optional<Product> buscarPorNombre(@Param("name") String name);

//    @Query("SELECT p FROM Product p JOIN FETCH c.category  WHERE p.category = :categoryId")
//    List<Product> buscarPorCategoria(@Param("category") Integer categoryId);



    @Query("SELECT p FROM Product p JOIN FETCH p.category c WHERE c.id = :categoryId")
    List<Product> findAllByCategoryId(@Param("categoryId") Integer categoryId);
}
