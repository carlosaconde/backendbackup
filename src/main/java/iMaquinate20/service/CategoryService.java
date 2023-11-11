package iMaquinate20.service;

import iMaquinate20.models.Category;
import iMaquinate20.models.Product;
import iMaquinate20.respository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service("category")
public class CategoryService implements IService<Category>{

@Autowired
private ICategoryRepository repository;

public CategoryService(ICategoryRepository repository){
    this.repository=repository;
}

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Category> getById(Integer id) {
        return repository.findById(String.valueOf(id));
    }

    @Override
    @Transactional
    public Category create(Category category) {
        try{
            Optional<Category> exists = buscarPorNombreDeCategoria(category.getCategoryName());

            if(exists.isPresent()){
                throw new IllegalArgumentException("la categoria ya existe");
            }
            return repository.save(category);
        }catch (Exception e){
            throw e;
        }
    }

    @Transactional
    public Optional<Category> buscarPorNombreDeCategoria(String categoryName) {

    try{
        return repository.buscarPorNombreDeCategoria(categoryName);
    }catch (Exception exception){
        throw exception;
    }
}

    @Override
    public Category update(Integer id, Category category) {
        try{
            Optional<Category> exists = getById(id);
            if (exists.isEmpty()){
                throw new IllegalArgumentException("el id no existe");
            }
            Category catExist = exists.get();
            catExist.setCategoryName(category.getCategoryName());
            catExist.setDescription(category.getDescription());
            catExist.setImageUrl(category.getImageUrl());

            return repository.save(catExist);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        try{
            Optional<Category> category = getById(id);
            if(category.isPresent()){
                repository.deleteById(String.valueOf(id));
            }else {
                throw new IllegalArgumentException("el id no existe");
            }

        }catch (Exception e){
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
