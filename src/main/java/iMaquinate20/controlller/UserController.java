package iMaquinate20.controlller;

import iMaquinate20.models.User;
import iMaquinate20.service.UserService;
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
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    @Qualifier("user")
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> create(@Valid @RequestBody User body, BindingResult result){
        try {
            if(result.hasErrors()){
                return null;
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(body));
        }catch (Exception e){
            throw  e;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User body,BindingResult result){
        try{
            Optional<User> userOptional =userService.buscarPorEmail(body.getEmail());
            if(userOptional.isPresent()&& (userOptional.get().getPassword1().equals(body.getPassword1()) ) ){
                return ResponseEntity.ok(userOptional.get());
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @GetMapping("/")
    public ResponseEntity<List<User>>  getAll(){
        try{
            List<User> users = userService.getAll();
            return ResponseEntity.ok().body(users);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }



}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            throw e;
        }
    }
    }
