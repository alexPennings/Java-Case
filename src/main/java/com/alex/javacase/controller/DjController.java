package com.alex.javacase.controller;

import com.alex.javacase.entitys.Dj;
import com.alex.javacase.repository.DjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DjController {

    @Autowired
    DjRepository djRepository;



    @GetMapping("/dj")
    public ArrayList<Dj> getAllDj(){
        return (ArrayList<Dj>) djRepository.findAll();
    }

    @GetMapping("/dj/specficdj")
    public ResponseEntity<Dj> getSpecificDjById(@RequestParam int id){
        Optional<Dj> optionalDj = djRepository.findById(id);
        //validate valid use of ternary
        return djRepository.findById(id).isPresent() ? new ResponseEntity<Dj>(optionalDj.get(), HttpStatus.OK) : new ResponseEntity<Dj>(new Dj(""), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dj/byname")
    public ArrayList<Dj> getDjListByName(@RequestParam String name){
        return djRepository.findAllByName(name);
    }

    @GetMapping("/dj/namelengthsix")
    public ArrayList<Dj> getDjByNameLengthSix(){
        return  djRepository.findAll().stream()
                .filter(dj -> dj.getName().length() > 6)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @PostMapping("/dj")
    public ArrayList<Dj> createDj(@RequestBody Dj dj){
        Dj newDj = new Dj(dj.getName());
        djRepository.save(newDj);

        return (ArrayList<Dj>) djRepository.findAll();
    }

    @DeleteMapping("/dj")
    public ArrayList<Dj> deleteDj(@Valid @RequestParam int id){
        Optional<Dj> optionalDjToDelete = djRepository.findById(id);

        if(optionalDjToDelete.isPresent() == true){
            Dj djToDelete =  optionalDjToDelete.get();
      djRepository.delete(djToDelete);
        }else{
            System.out.println("Failed to find Dj to delete");

        }
        return (ArrayList<Dj>) djRepository.findAll();
    }

    @PutMapping("/dj")
    public Dj editDj(@Valid @RequestBody Dj receivedDj){
        Optional<Dj> optionalDjToEdit = djRepository.findById(receivedDj.getId());

        if(optionalDjToEdit.isPresent() == true){
            Dj djToEdit = optionalDjToEdit.get();
            djToEdit.setName(receivedDj.getName());

            djRepository.save(djToEdit);
        }else {
            System.out.println("couldnt find Dj to edit");
        }
        return receivedDj;
    }
}
