package iMaquinate20.service;
import iMaquinate20.models.Category;
import iMaquinate20.models.Characteristics;
import iMaquinate20.models.Product;
import iMaquinate20.respository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service("product")
public class ProductService implements IService<Product> {

    @Autowired
    public IService<Category> categoryIService;
    @Autowired
    private IProductRepository repository;
    @Autowired
    CharacteristicsService characteristicsService;
    public ProductService(IProductRepository repository){
        this.repository=repository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product create(Product product) {
        try {
            Optional<Product> isExists = buscarPorNombre(product.getName());

            if(isExists.isPresent()){
                throw new IllegalArgumentException("el nombre ya existe");
            }

            return repository.save(product);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public Product update(Integer id, Product product) {
        try{
            Optional<Product> exists = getById(id);
            if(exists.isEmpty()){
                throw new IllegalArgumentException("El id no existe");
            }
            Product productExist =exists.get();
            productExist.setName(product.getName());
            productExist.setDescription(product.getDescription());
            productExist.setCategory(product.getCategory());
            productExist.setPrice(product.getPrice());
            productExist.setImageUrl(product.getImageUrl());

            return repository.save(productExist);
        }catch (Exception e){
            throw e;
        }
    }
    @Override
    @Transactional
    public void delete(int id){
        try{
            Optional<Product> product = getById(id);
            if(product.isPresent()){
                repository.deleteById(id);
            }else{
                throw new IllegalArgumentException("El id" +id +"no existe");
            }
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public List<Product> getProductsByCategory(int id) {
        try {
            return repository.findAllByCategoryId(id);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void sendEmail(String[] toUser, String subject, String message) {

    }

    @Transactional
    public Optional<Product> buscarPorNombre(String nombre){
        try{
            return repository.buscarPorNombre(nombre);
        }catch (Exception exception){
            throw exception;
        }
    }






}