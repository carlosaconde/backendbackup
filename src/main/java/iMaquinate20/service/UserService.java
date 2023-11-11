package iMaquinate20.service;

import iMaquinate20.models.Product;
import iMaquinate20.models.Rol;
import iMaquinate20.models.User;
import iMaquinate20.respository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("user")

public class UserService implements IService<User>{


    @Autowired
    private IUserRepository repository;

    public UserService(IUserRepository repository){
        this.repository=repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional

    public User create(User body){
        try{
            Optional<User> exist = buscarPorEmail(body.getEmail());
            if(exist.isEmpty()) {

                if(body.getIsAdmin()){
                    body.setRol(Rol.ROLE_ADMIN);
                    return repository.save(body);
                } else {
                    body.setRol(Rol.ROLE_USER);
                    return repository.save(body);
                }

            }else {
                return null;
            }

        }catch (Exception e){
            throw e;
        }

    }



    public Optional<User> buscarPorEmail(String email) {
        try{
            return repository.buscarPorEmail(email);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public User update(Integer id, User user) {
        return null;
    }

    @Override
    @Transactional
    public void delete(int id) {
    try{
        Optional<User> user=getById(id);
        if(user.isPresent()){
            repository.deleteById(id);
        }else {
            throw new IllegalArgumentException("el id no existe");
        }
    } catch (Exception e){
        throw e;
    }
    }

    @Override
    public List<Product> getProductsByCategory(int id) {
        return null;
    }

    @Override
    public void sendEmail(String[] toUser, String subject, String message) {

    }

}
