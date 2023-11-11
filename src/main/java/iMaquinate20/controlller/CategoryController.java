package iMaquinate20.controlller;

import iMaquinate20.models.Category;
import iMaquinate20.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    @Qualifier("category")
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, BindingResult result){
        try {
            if(result.hasErrors()){
                return null;
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(category));
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> category(@PathVariable Integer id){
        try{
            Optional<Category> categoryOptional=categoryService.getById(id);
            if(categoryOptional.isPresent()){
                return ResponseEntity.ok(categoryOptional.get());
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAll(){
        try{
            List<Category> categories = categoryService.getAll();
            return ResponseEntity.ok().body(categories);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
