package iMaquinate20.controlller;


import iMaquinate20.models.Characteristics;
import iMaquinate20.service.CharacteristicsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characteristics")

public class CharacteristicsController {

    @Autowired
    @Qualifier("characteristics")
    CharacteristicsService characteristicsService;

    @PostMapping("/")
    public ResponseEntity<Characteristics> create(@Valid @RequestBody Characteristics characteristics, BindingResult result){
        try {
            if(result.hasErrors()){
                return null;
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(characteristicsService.create(characteristics));
        }catch (Exception e){
            throw e;
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Characteristics>> getAll(){
        try{
            List<Characteristics> characteristics = characteristicsService.getAll();
            return ResponseEntity.ok().body(characteristics);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
