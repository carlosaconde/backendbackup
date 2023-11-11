package iMaquinate20.service;

import iMaquinate20.models.Category;
import iMaquinate20.models.Product;

import java.util.List;
import java.util.Optional;

public interface IService <T>{
    List<T> getAll();

    Optional<T> getById(Integer id);

    T create (T t);

    T update (Integer id,T t);

    void delete (int id);


    List<Product> getProductsByCategory(int id);

    void sendEmail(String[] toUser, String subject, String message);


}
