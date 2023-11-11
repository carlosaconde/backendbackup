package iMaquinate20.controlller;
import iMaquinate20.models.Product;
import iMaquinate20.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    @Qualifier("product")
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?>  producto(@PathVariable Integer id){
        try {
            Optional<Product> productOptional = productService.getById(id);
            if(productOptional.isPresent()){
                return ResponseEntity.ok(productOptional.get());
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/addproduct")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product, BindingResult result){
        try {
            if (result.hasErrors()){
                return null;
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
        }catch (Exception e){
            throw  e;
        }
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> buscarPorCategoria( @PathVariable int id){
        try{
            List<Product> products = productService.getProductsByCategory(id);
            return ResponseEntity.ok().body(products);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/")
  public ResponseEntity<List<Product>> getAll(){
        try{
            List<Product> products = productService.getAll();
            return  ResponseEntity.ok().body(products);
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,@PathVariable int id){
        try{
            if(result.hasErrors()){
                throw new RuntimeException();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.update(id,product) );

        }catch (Exception e){
            throw  e;
        }
    }


@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            productService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            throw e;
        }
}


}
