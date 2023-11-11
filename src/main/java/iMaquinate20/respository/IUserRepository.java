package iMaquinate20.respository;

import iMaquinate20.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> buscarPorEmail(@Param("email") String email);
}
